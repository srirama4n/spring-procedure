package in.codeislife.springprocedure.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
public class UserResource {

    private String name;

    private String email;
}
