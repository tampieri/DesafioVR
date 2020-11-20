package br.com.vr.entity.response;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.vr.entity.PedidoVO;

public class ServiceResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("ListaPedido")
	private List<PedidoVO> pedidoVO;
}
