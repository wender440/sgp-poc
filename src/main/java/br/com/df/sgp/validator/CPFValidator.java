package br.com.df.sgp.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.df.sgp.util.CPFUtils;


/**
 * 
 * @author Wenderson Ferreira
 *
 */
@FacesValidator("CPFValidator")
public class CPFValidator implements Validator {

	@Override
	public void validate(FacesContext ctx, UIComponent ui, Object value) throws ValidatorException {
		if(value != null && !value.equals("")){
			String cpf = (String) value;
			boolean valid = CPFUtils.validate(cpf);
			if(!valid){
				FacesMessage msg = new FacesMessage("CPF Inválido", "CPF Inválido");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
			}
		}

	}
}
