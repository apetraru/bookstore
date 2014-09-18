package com.bk.bean.customer;

import static com.bk.util.Message.error;
import static com.bk.util.Message.globalError;
import static com.bk.util.Message.info;
import static com.bk.util.Message.msg;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bk.bean.SessionBean;
import com.bk.model.Customer;
import com.bk.model.Image;
import com.bk.service.CustomerService;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@Component
@Scope("view")
public class DetailsBean {

	@Autowired private CustomerService customerService;
	@Autowired private SessionBean sessionBean;

	private Customer customer;
	private Image image;

	@PostConstruct
	public void init() {
		customer = sessionBean.getLoggedInUser();
		image = new Image();
		if (customer == null) {
			globalError(msg("loginFirst"));
		}
	}

	public void update() {
		if (usernameExists()) {
			error("table:profileForm:username", msg("usernameTaken"));
			return;
		}

		if (emailExists()) {
			error("table:profileForm:email", msg("emailTaken"));
			return;
		}
		sessionBean.setLoggedInUser(customerService.save(customer));
		image = new Image();
		info("table:profileForm:updateButton", msg("detailsUpdated"));
	}

	public void upload(FileUploadEvent event) throws IOException {
		UploadedFile uploadedFile = event.getFile();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		InputStream stream = uploadedFile.getInputstream();
		byte[] buffer = new byte[(int) uploadedFile.getSize()];
		int read;
		try {
			while ((read = stream.read(buffer)) != -1) {
				baos.write(buffer, 0, read);
			}
		} finally {
			stream.close();
		}
		image.setName(uploadedFile.getFileName());
		image.setSize(uploadedFile.getSize());
		image.setFileContent(baos.toByteArray());
		image.setContentType(uploadedFile.getContentType());
		customer.setImage(image);
	}

	private boolean usernameExists() {
		Customer duplicateCustomer = customerService.findByUsername(customer
				.getUsername());
		if (duplicateCustomer == null) {
			return false;
		}

		return (customer.getUsername().equals(duplicateCustomer.getUsername()) && !duplicateCustomer
				.getId().equals(customer.getId()));
	}

	private boolean emailExists() {
		Customer duplicateCustomer = customerService.findByEmailAddress(customer
				.getEmailAddress());
		if (duplicateCustomer == null) {
			return false;
		}

		return (customer.getEmailAddress().equals(duplicateCustomer.getEmailAddress()) && !duplicateCustomer
				.getId().equals(customer.getId()));
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
