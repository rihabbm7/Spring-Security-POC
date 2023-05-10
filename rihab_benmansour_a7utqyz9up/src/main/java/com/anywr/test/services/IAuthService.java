package com.anywr.test.services;

import com.anywr.test.dao.dto.AuthenticationRequest;
import com.anywr.test.dao.dto.AuthenticationResponse;

public interface IAuthService {

	AuthenticationResponse authenticate(AuthenticationRequest request);


}
