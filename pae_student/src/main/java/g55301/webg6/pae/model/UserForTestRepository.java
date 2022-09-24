package g55301.webg6.pae.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserForTestRepository extends JpaRepository<UserForTest, String> {

    public UserForTest findByName(String name);

    @Query("select u FROM UserForTest u WHERE length(u.login)>12")
    public List<UserForTest> findByLongLogin();

}
