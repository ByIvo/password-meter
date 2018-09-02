package rocks.byivo.passwordmeter.measure;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rocks.byivo.passwordmeter.measure.service.PasswordMeterService;
import rocks.byivo.passwordmeter.model.PasswordMeasureRequest;
import rocks.byivo.passwordmeter.model.PasswordMeasureResult;

@RestController
public class PasswordMeterController {

    @Autowired
    private PasswordMeterService passwordMeasureService;
    
    @RequestMapping(path = "/measure", method = POST)
    public PasswordMeasureResult measure(@RequestBody(required = true) PasswordMeasureRequest passwordRequest) {
	return passwordMeasureService.measure(passwordRequest);
    }
}
