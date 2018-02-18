package com.mycorp;

import org.springframework.beans.factory.annotation.Autowired;

import util.datos.UsuarioAlta;

public class Zen {

	@Autowired
	static ZendeskService zendeskService;
	
	private static final String UA_NUM_POLIZA = "123456789X";
	private static final String UA_NUM_DOC_ACREDITATIVO = "4758952R";
	private static final String UA_NUM_TARJETA = "1234567891234";
	private static final int UA_TIPO_DOC_ACREDITATIVO = 0;
	private static final String UA_EMAIL = "usuario_alta@mail.com";
	private static final String UA_NUMERO_TELEFONO = "123456789";
	
	public static void main(String[] args) {
		
		test();

	}
	
	public static void test(){
		
		zendeskService.altaTicketZendesk(obtainUsuarioAlta(), "mozillas");
	}
	
	private static UsuarioAlta obtainUsuarioAlta(){
		
		UsuarioAlta usuarioAlta = new UsuarioAlta();
		
		usuarioAlta.setNumPoliza(UA_NUM_POLIZA);
		usuarioAlta.setNumDocAcreditativo(UA_NUM_DOC_ACREDITATIVO);
		usuarioAlta.setNumTarjeta(UA_NUM_TARJETA);
		usuarioAlta.setTipoDocAcreditativo(UA_TIPO_DOC_ACREDITATIVO);
		usuarioAlta.setEmail(UA_EMAIL);
		usuarioAlta.setNumeroTelefono(UA_NUMERO_TELEFONO);
		
		return usuarioAlta;
	}

}
