package api.management.task.infrastructure.mapper;

import api.management.task.infrastructure.entity.Customer;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerMapper {

    List<Customer> findAll();

}
