
package edu.wustl.dynamicextensions.formdesigner.resource.facade;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import edu.common.dynamicextensions.domain.nui.Container;
import edu.common.dynamicextensions.domain.nui.Control;
import edu.common.dynamicextensions.domain.nui.SelectControl;
import edu.common.dynamicextensions.domain.nui.UserContext;
import edu.wustl.dynamicextensions.formdesigner.mapper.ContainerMapper;
import edu.wustl.dynamicextensions.formdesigner.mapper.ControlMapper;
import edu.wustl.dynamicextensions.formdesigner.mapper.DefaultControlMapper;
import edu.wustl.dynamicextensions.formdesigner.mapper.Properties;
import edu.wustl.dynamicextensions.formdesigner.mapper.PvMapper;
import edu.wustl.dynamicextensions.formdesigner.mapper.RegularContainerMapper;
import edu.wustl.dynamicextensions.formdesigner.utility.CSDConstants;

public class ContainerFacade {

	private Container container = null;
	private UserContext userContext = null;
	private static ContainerMapper containerMapper = new RegularContainerMapper();

	/**
	 * @param container
	 */
	private ContainerFacade(Container container, UserContext userContext) {
		this.container = container;
		this.userContext = userContext;
	}

	/**
	 * @param containerProps
	 * @return
	 * @throws Exception 
	 */
	public static ContainerFacade createContainer(Properties containerProps, UserContext userContext) throws Exception {
		Container container = containerMapper.propertiesToContainer(containerProps, userContext);
		
		containerMapper.setRootContainer(container);
		return new ContainerFacade(container, userContext);
	}

	public void updateContainer(Properties containerProps) throws Exception {
		Container containerFromUI = containerMapper.propertiesToContainer(containerProps, null);
		container.setManagedTables(false);
		if (container.getLayouts() != null) {
			containerFromUI.setLayouts(new ArrayList<>(container.getLayouts()));
		}

		container.editContainer(containerFromUI);
		//containerMapper.propertiesToContainer(containerProps, container, null);
	}

	/**
	 * @param id
	 * @param edit TODO
	 * @return
	 */
	public static ContainerFacade loadContainer(Long id, UserContext userContext, boolean edit) {
		Container newContainer = Container.getContainer(id);
		if (newContainer.getCreatedBy().equals(userContext.getUserId())) {
			edit = true;
		}
		if (!edit) {
			Container containerReplica = newContainer.getReplica();
			newContainer = containerReplica;
		}
		containerMapper.setRootContainer(newContainer);
		return new ContainerFacade(newContainer, userContext);
	}

	public Long getCreatedBy() {
		return container.getCreatedBy();
	}

	/**
	 * @param controlProps
	 * @throws Exception 
	 */
	public void createControl(Properties controlProps) throws Exception {
		String type = controlProps.getString(CSDConstants.CONTROL_TYPE);
		if(type.equals(CSDConstants.FANCY_CONTROL)) {
			type = controlProps.getString(CSDConstants.FANCY_CONTROL_TYPE);
		}
		Control control = ((DefaultControlMapper)ControlMapper.getInstance().getControlMapper(type)).propertiesToControl(controlProps, null);
		container.addControl(control);
	}

	public void persistContainer() {
		container.save(userContext);
	}

	public String getHTML(HttpServletRequest request) {
		return null;
	}

	public Properties getProperties() throws Exception {
		containerMapper.setRootContainer(container);
		return containerMapper.containerToProperties(container);
	}

	public File getPvFile(String controlName) throws IOException {

		Control control = container.getControl(controlName, "\\.");
		File pvFile = null;
		if (control instanceof SelectControl) {
			String uploadedFileLocation = "/tmp/" + new Date().getTime() + "_Permissible_Values.csv";
			pvFile = PvMapper.getPvFile(uploadedFileLocation, ((SelectControl) control).getPvs());
		}
		return pvFile;
	}

}
