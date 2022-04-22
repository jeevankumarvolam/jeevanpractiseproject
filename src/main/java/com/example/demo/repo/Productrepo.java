package com.example.demo.repo;

import com.example.demo.entity.BrandMarketChannel;
import com.example.demo.entity.response.ProductResponse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface Productrepo extends MongoRepository<ProductResponse,UUID> {

	@Query(value= "{'description':?0}")
	List<ProductResponse> findByBrandmarketchannelBrand(String description);

	List<ProductResponse> findByPriceGreaterThan(Integer price);
	ProductResponse findByBrandmarketchannelAndPrice(BrandMarketChannel brandMarketChannel,Integer price);

	ProductResponse findByBrandmarketchannelAndPriceOrderByUnits(BrandMarketChannel brandMarketChannel,Integer price);


//	ProductResponse findByBrandAndSortByUnits(BrandMarketChannel brandMarketChannel,Integer price);


	@Query(value = "{" +
			"   'brandmarketchannel.brand' :?0," +
			"}",
			count = true
	)
    List<ProductResponse> findByBrand(String brand);
//	@Query(value = "{id : ?0}")
//	@Query(value="{units:?0,price:?1}")
//	List<ProductResponse> findByUnits(Integer units,Integer price);

//@Query(value = "{units:{$gt:?0},price:{$lt:?1}}")
//	List<ProductResponse> findProductsAbove(Integer units,Integer price);
//@Query(value = "{$or:[{units:?0},{price:?1}]}",fields ="{'brandmarketchannel.channel':1}" )
//List<String> find(Integer units,Integer price);
//@Query(value="{units:{$gt:?0}}",count=true)
//	Integer find(Integer units);
//@Query(value = "{units:?0}",exists = true)
//	boolean findBy(Integer units);
//@Query(value = "{units:{$gt:?0}}",sort = "{description:1}")
//List<ProductResponse> findBy(Integer units);
//@Query(value = "{units:{$gt:?0}}",delete = true)
//	Long findBy(Integer units);

}
