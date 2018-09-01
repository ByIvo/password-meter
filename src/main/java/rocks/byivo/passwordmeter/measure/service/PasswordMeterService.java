package rocks.byivo.passwordmeter.measure.service;

import rocks.byivo.passwordmeter.model.PasswordMeasureRequest;
import rocks.byivo.passwordmeter.model.PasswordMeasureResult;

public interface PasswordMeterService {

    PasswordMeasureResult measure(PasswordMeasureRequest passwordMeasureRequest);
}
