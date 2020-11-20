package br.com.vr.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.vr.entity.ItemVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PedidoNewDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@JsonProperty(value = "status")
	private String status;
	
	@JsonProperty(value = "dataVencimento")
	private Date dataVencimento;
	
	@JsonProperty(value = "itens")
	private List<ItemVO> itens;

}
