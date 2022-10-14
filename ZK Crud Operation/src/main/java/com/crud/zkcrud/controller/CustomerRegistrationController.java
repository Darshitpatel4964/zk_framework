package com.crud.zkcrud.controller;

import javax.servlet.http.HttpServletRequest;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;
import com.crud.zkcrud.constant.Constant;
import com.crud.zkcrud.modelbean.Customer;
import com.crud.zkcrud.service.CustomerService;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class CustomerRegistrationController extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;
	
	@WireVariable
	private transient CustomerService customerService; 

	private transient Customer customer;

	CustomerListController listController;

	@Wire
	private Grid formGrid;

	@Wire
	private Textbox firstnameBox;
	
	@Wire
	private Textbox lastnameBox;

	@Wire
	private Datebox dobBox;

	@Wire
	private Textbox ageBox;

	@Wire
	private Textbox emailBox;

	@Wire
	private Textbox mobileBox;

	@Wire
	private Radiogroup genderRadio; 

	@Wire
	private Radio maleRadio;

	@Wire
	private Radio femaleRadio;

	@Wire
	private Textbox address1Box;

	@Wire
	private Textbox address2Box;

	@Wire
	private Button submitButton;

	@Override
	public void doAfterCompose(Component component) throws Exception {
		super.doAfterCompose(component);
		HttpServletRequest request = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
		customer = (Customer) request.getAttribute("CustomerProperties");
		listController = (CustomerListController) request.getAttribute("mainController");

		if(customer != null && customer.getId()>0) {
			firstnameBox.setValue(customer.getFirstName());
			lastnameBox.setValue(customer.getLastName());
			dobBox.setValue(customer.getDateOfBirth()); 
			ageBox.setValue(customer.getAge());
			emailBox.setValue(customer.getEmail()); 
			mobileBox.setValue(customer.getMobile());
			genderRadio.setId(customer.getGender());
			if(Integer.valueOf(genderRadio.getId())==0) { 
				maleRadio.setSelected(true);
			} else { 
				femaleRadio.setSelected(true); 
			}
			address1Box.setValue(customer.getAddress1());
			address2Box.setValue(customer.getAddress2());
		}
	}


	@Listen("onChange=#emailBox;")
	public void emailAddress() {
		try {
			String email = emailBox.getText();
			String checkEmailAddress = customerService.checkEmailAddress(email);
			if(email != null && Constant.DUPLICATE_VALUE.equals(checkEmailAddress)) {
				emailBox.setErrorMessage("Email Address is already used");
			}	
			submitButton.setDisabled(Constant.DUPLICATE_VALUE.equals(checkEmailAddress)); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Listen("onChange=#mobileBox;")
	public void mobileNumber() {
		try {
			String mobile = mobileBox.getText() != null ? mobileBox.getText() : "" ;
			String checkMobileNumber = customerService.checkMobileNumber(mobile);
			if(mobile != null && Constant.DUPLICATE_VALUE.equals(checkMobileNumber)) {
				mobileBox.setErrorMessage("Mobile Number is already used");
			}
			submitButton.setDisabled(Constant.DUPLICATE_VALUE.equals(checkMobileNumber)); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Listen("onClick=#submitButton")
	public void saveInfo() {
		if (customer == null) {
			customer = new Customer();
			customer.setId(0L);
		}
		customer.setFirstName(firstnameBox.getValue());
		customer.setLastName(lastnameBox.getValue());
		customer.setDateOfBirth(dobBox.getValue());
		customer.setAge(ageBox.getValue());
		customer.setEmail(emailBox.getValue());
		customer.setMobile(mobileBox.getValue());
		customer.setGender(genderRadio.getId());
		customer.setAddress1(address1Box.getValue());
		customer.setAddress2(address2Box.getValue());
		if ("Male".equals(genderRadio.getSelectedItem().getLabel())) {
			customer.setGender("0");
		} else {
			customer.setGender("1");
		}
		customerService.save(customer);
		Executions.getCurrent().sendRedirect("~./customer-list.zul");
	}

}