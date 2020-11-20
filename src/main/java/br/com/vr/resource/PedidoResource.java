package br.com.vr.resource;

import java.net.URI;
import java.text.ParseException;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.vr.dto.PedidoDTO;
import br.com.vr.dto.PedidoNewDTO;
import br.com.vr.entity.PedidoVO;
import br.com.vr.service.PedidoService;

@RequestMapping("/v1/pedidos")
@RestController
public class PedidoResource {

	private final PedidoService pedidoService;
	
	public PedidoResource(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}
	
	@GetMapping(value="/{idPedido}")
	public ResponseEntity<PedidoVO> consultar(
			@PathVariable Integer idPedido) throws Exception {	
		
		PedidoVO response = pedidoService.consultar(idPedido);		
		return ResponseEntity.ok().body(response);
	}

	@GetMapping(value="/page")
	public ResponseEntity<Page<PedidoDTO>> listar(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="idPedido") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<PedidoVO> list = pedidoService.findPage(page, linesPerPage, orderBy, direction);
		Page<PedidoDTO> listDto = list.map(obj -> new PedidoDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}
	
	@PostMapping
	public ResponseEntity<Void> criar(@RequestBody PedidoNewDTO objDTO) {
		PedidoVO obj = pedidoService.fromDTO(objDTO);
		obj = pedidoService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{idPedido}").buildAndExpand(obj.getIdPedido()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value="/{idPedido}")
	public ResponseEntity<Void> alterar(@RequestBody PedidoDTO objDTO, @PathVariable Integer idPedido){
			PedidoVO obj = pedidoService.fromDTO(objDTO);
			obj.setIdPedido(idPedido);
			obj = pedidoService.update(obj);
			return ResponseEntity.noContent().build();
	
	}

	@DeleteMapping(value = "/{idPedido}")
	public ResponseEntity<Void> excluir(@PathVariable Integer idPedido){
		pedidoService.delete(idPedido);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value="/{idPedido}/status/{status}")
	public ResponseEntity<Void> atualizarStatus(@PathVariable String status, @PathVariable Integer idPedido){
		pedidoService.updateStatus(idPedido, status);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value="/{idPedido}/{dataVencimento}")
	public ResponseEntity<Void> atualizarVencimentoPedido(@PathVariable String dataVencimento, @PathVariable Integer idPedido) throws ParseException{
			pedidoService.updateDataVencimento(idPedido, dataVencimento);
			return ResponseEntity.noContent().build();
	}
}
