package br.com.df.sgp.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.df.sgp.util.EmailUtils;

/**
 * 
 * @author Wenderson Ferreira
 *
 */
@FacesValidator("EmailValidator")
public class EmailValidator implements Validator {

	@Override
	public void validate(FacesContext ctx, UIComponent ui, Object value) throws ValidatorException {
		if(value != null && !value.equals("")){
			String email = (String) value;
			boolean valid = EmailUtils.validate(email);
			if(!valid){
				FacesMessage msg = new FacesMessage("Email Inválido", "Email Inválido");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
			}
		}

	}
}
