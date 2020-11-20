package br.com.vr.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.vr.entity.PedidoVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PedidoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@JsonProperty(value = "idPedido")
	private Integer idPedido;
	
	@JsonProperty(value = "status")
	private String status;
	
	@JsonProperty(value = "dataVencimento")
	private Date dataVencimento;
	
	public PedidoDTO(PedidoVO obj) {
		idPedido = obj.getIdPedido();
		status = obj.getStatus().getDescricao();
		dataVencimento = obj.getDataVencimento();
	}
}
