package com.crud.zkcrud.controller;

import java.util.List;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Include;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;
import com.crud.zkcrud.modelbean.Customer;
import com.crud.zkcrud.service.CustomerService;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class CustomerListController extends SelectorComposer<Window>{

	private static final long serialVersionUID = 1L;

	@WireVariable
	private transient CustomerService customerService;

	private transient Customer customer;

	@Wire
	private Grid customerGrid;

	@Wire
	private Include includeTag;

	private transient ListModelList<Customer> customerData = new ListModelList<>();

	@Override
	public void doAfterCompose(Window window) throws Exception { 
		super.doAfterCompose(window);
		loadingList();
	}

	private void loadingList() {
		List<Customer> result = customerService.findAll();
		customerData.addAll(result);
		customerGrid.setModel(customerData);
	}

	@Listen("onDeleteButton=#searchButton")
	public void deleteCustomer(ForwardEvent event) {
		if(customerService != null) {
			customer = (Customer) event.getData();
			customerService.deleteCustomer(customer);
			loadingList();
			Executions.getCurrent().sendRedirect("~./customer-list.zul");
		}
	}

	@Listen("onEditButton=#searchButton")
	public void forwardData(ForwardEvent event) {
		customer = (Customer) event.getData();
		includeTag.setDynamicProperty("CustomerProperties", customer);
		includeTag.setDynamicProperty("mainController", this);
		includeTag.setSrc("~./customer-form.zul");
	}

}