package mapper;

import dto.ProductDto;
import model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper()
public interface ProductMapper {

    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    ProductDto productToProductDto(Product source);

    @Mappings(
            @Mapping(target = "id", ignore = true)
    )
    Product productDtoToProduct(ProductDto source);
}
