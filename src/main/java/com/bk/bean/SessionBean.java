package com.bk.bean;

import com.bk.model.Customer;
import com.bk.service.BookService;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
@Scope("session")
public class SessionBean {
	@Autowired private BookService bookService;
	
	
	private Customer loggedInUser;
	private boolean loggedOn;

	public Customer getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(Customer loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

	public boolean isLoggedOn() {
		return loggedOn;
	}

	public void setLoggedOn(boolean loggedOn) {
		this.loggedOn = loggedOn;
	}

	public StreamedContent getImage() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE || !loggedOn || loggedInUser.getImage() == null) {
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			InputStream input = externalContext.getResourceAsStream("/resources/images/default-user.png");
			return new DefaultStreamedContent(input);
		}
		else {
			return new DefaultStreamedContent(new ByteArrayInputStream(loggedInUser.getImage().getFileContent()));
		}
	}

}
