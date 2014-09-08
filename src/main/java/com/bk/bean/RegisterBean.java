package com.bk.bean;

import static com.bk.util.Message.error;
import static com.bk.util.Message.info;
import static com.bk.util.Message.msg;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Component;

import com.bk.model.Customer;
import com.bk.model.Role;
import com.bk.service.CustomerService;
import com.bk.service.RoleService;

/**
 * @author Andrei Petraru
 * Date: 1/16/13
 */

@Component
@Scope("request")
public class RegisterBean {
	
	private static final String REGISTER_BUTTON = "registerForm:register";

	@Autowired private CustomerService customerService;
	@Autowired private ShaPasswordEncoder passwordEncoder;
	@Autowired private RoleService roleService;

    private String username;
    private String password;
    private String email;

    public void register() {

        if (checkExistingUser()) {
            return;
        }

        if (checkExistingEmail()) {
            return;
        }

        Customer newCustomer = new Customer();
		Role userRole = roleService.getUserRole();
        newCustomer.setUsername(username);
        newCustomer.setPassword(passwordEncoder.encodePassword(password, null));
        newCustomer.setEmailAddress(email);

        if (customerService.save(newCustomer) != null) {
			newCustomer.setRole(userRole);
			customerService.save(newCustomer);
            clearFields();
            info(REGISTER_BUTTON, msg("registrationSuccess"));
        } else {
            error("registerForm:register", msg("registrationFailed"));
        }

    }

    private boolean checkExistingUser() {
        Customer existingCustomer = customerService.findByUsername(username);
        if (existingCustomer == null) {
            return false;
        }
        String existingUsername = existingCustomer.getUsername();
        if (StringUtils.equalsIgnoreCase(username, existingUsername)) {
            error("registerForm:username", msg("usernameTaken"));
            return true;
        }
        return false;
    }

    public boolean checkExistingEmail() {
        Customer existingCustomer = customerService.findByEmailAddress(email);
        if (existingCustomer == null) {
            return false;
        }
        String existingEmail = existingCustomer.getEmailAddress();
        if (StringUtils.equalsIgnoreCase(email, existingEmail)) {
            error("registerForm:email", msg("emailTaken"));
            return true;
        }
        return false;
    }

    private void clearFields() {
        username = "";
        password = "";
        email = "";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
