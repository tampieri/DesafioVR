package br.com.vr.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.vr.dto.PedidoDTO;
import br.com.vr.dto.PedidoNewDTO;
import br.com.vr.emun.Status;
import br.com.vr.entity.ItemVO;
import br.com.vr.entity.PedidoVO;
import br.com.vr.repository.ItemRepository;
import br.com.vr.repository.PedidoRepository;

@Service
public class PedidoService {

	private final PedidoRepository pedidoRepository;
	
	public PedidoService(PedidoRepository pedidoRepository) {
		this.pedidoRepository = pedidoRepository;
	}
	
	@Autowired
	private ItemRepository itemRepository;
	
	public PedidoVO consultar(Integer idPedido){
		Optional<PedidoVO> obj = pedidoRepository.findById(idPedido);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: ", idPedido.toString()));
	}

	public Page<PedidoVO> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return pedidoRepository.findAll(pageRequest);
	}

	public PedidoVO fromDTO(PedidoNewDTO objDTO) {
		Status status = objDTO.getStatus().equals("Ativo")?Status.ATIVO:Status.INATIVO;
		PedidoVO ped = new PedidoVO(null, status, objDTO.getDataVencimento());
		List<ItemVO> listItem = new ArrayList<>();
		for (ItemVO itemVO : objDTO.getItens()) {
			ItemVO item = new ItemVO(null, itemVO.getDescricao(), ped);
			listItem.add(item);
		}
		ped.setItens(listItem);		
		return ped;
	}
	
	public PedidoVO fromDTO(PedidoDTO objDTO) {
		Status status = objDTO.getStatus().equals("Ativo")?Status.ATIVO:Status.INATIVO;
		return new PedidoVO(objDTO.getIdPedido(), status, objDTO.getDataVencimento());
	}

	public PedidoVO insert(PedidoVO obj) {
		obj.setIdPedido(null);
		obj = pedidoRepository.save(obj);
		itemRepository.saveAll(obj.getItens());
		return obj;
	}

	public PedidoVO update(PedidoVO obj) {
		PedidoVO newObj = consultar(obj.getIdPedido());
		updateData(newObj, obj);
		return pedidoRepository.save(newObj);
	}

	private void updateData(PedidoVO newObj, PedidoVO obj) {
		newObj.setStatus(obj.getStatus());
		newObj.setDataVencimento(obj.getDataVencimento());
	}
	
	public void updateStatus(Integer idPedido, String status) {
		PedidoVO obj = consultar(idPedido);
		Status st = status.equalsIgnoreCase("Ativo")?Status.ATIVO:Status.INATIVO;
		obj.setStatus(st);
		pedidoRepository.save(obj);		
	}
	
	public void updateDataVencimento(Integer idPedido, String dataVencimento) throws ParseException {
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy"); 
		Date dataFormatada = formato.parse(dataVencimento);
		PedidoVO obj = consultar(idPedido);
		obj.setDataVencimento(dataFormatada);
		pedidoRepository.save(obj);		
	}

	public void delete(Integer idPedido) {
		consultar(idPedido);
		try {
			pedidoRepository.deleteById(idPedido);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possível excluir porque há registros relacionados");
		}
	}
}
