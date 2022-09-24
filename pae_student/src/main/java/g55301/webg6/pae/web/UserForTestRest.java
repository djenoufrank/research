package g55301.webg6.pae.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import g55301.webg6.pae.business.UserForTestService;
import g55301.webg6.pae.model.UserForTest;

@RestController
@RequestMapping("/api")
public class UserForTestRest {

  @Autowired
private UserForTestService userService;
  @GetMapping("/user/{name}")
public UserForTest getUserByName(@PathVariable("name") String name) {
 return userService.getUserByName(name);
 }
 }