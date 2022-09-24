package g55301.webg6.pae.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import g55301.webg6.pae.model.UserForTest;
import g55301.webg6.pae.model.UserForTestRepository;

@Service
public class UserForTestService {

@Autowired
private UserForTestRepository userRepository;

public UserForTest getUserByName(String name) {
return userRepository.findByName(name);
}
}
