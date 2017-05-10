package edu.common.dynamicextensions.domain.nui.factory;

import static edu.common.dynamicextensions.nutility.ParserUtil.getTextValue;

import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Element;

import edu.common.dynamicextensions.domain.nui.Container;
import edu.common.dynamicextensions.domain.nui.Control;
import edu.common.dynamicextensions.domain.nui.LinkControl;

public class LinkControlFactory extends AbstractControlFactory {
	public static LinkControlFactory getInstance() {
		return new LinkControlFactory();
	}

	@Override
	public String getType() {
		return "linkField";
	}

	@Override
	public Control parseControl(Element ele, int row, int xPos, Properties props) {
		LinkControl linkField = new LinkControl();
		setControlProps(linkField, ele, row, xPos);

		String formName = getTextValue(ele, "formName", null);
		if (StringUtils.isBlank(formName)) {
			throw new IllegalArgumentException("Form name is mandatory for link field");
		}

		Container form = Container.getContainer(formName);
		if (form == null) {
			throw new IllegalArgumentException("Invalid form name: " + formName);
		}

		linkField.setFormName(formName);
		return linkField;
	}
}
