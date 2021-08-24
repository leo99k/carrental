package carrental;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="contracts", path="contracts")
public interface ContractRepository extends PagingAndSortingRepository<Contract, Long>{


}
