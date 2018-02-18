package com.mycorp;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import util.datos.UsuarioAlta;

public class ZendeskServiceTest {
	
	private static final String UA_NUM_POLIZA = "123456789X";
	private static final String UA_NUM_DOC_ACREDITATIVO = "4758952R";
	private static final String UA_NUM_TARJETA = "1234567891234";
	private static final int UA_TIPO_DOC_ACREDITATIVO = 0;
	private static final String UA_EMAIL = "usuario_alta@mail.com";
	private static final String UA_NUMERO_TELEFONO = "123456789";
	
	private static final String UA_USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X x.y; rv:42.0) Gecko/20100101 Firefox/42.0";

	//@Autowired
	ZendeskService zendeskService;
	
	@Before
	public void init(){
		this.zendeskService = new ZendeskService();
	}
	
	@Test
	public void altaTicketZendeskshouldReturnDatosUsuario(){
		
		UsuarioAlta usuarioAlta = this.obtainUsuarioAlta();
		String userAgent = UA_USER_AGENT;
		
		String datosUsuario = zendeskService.altaTicketZendesk(usuarioAlta, userAgent);
		
		System.out.println("datosusuario");
		System.out.println(datosUsuario);
	}
	
	
	private UsuarioAlta obtainUsuarioAlta(){
		
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
