package br.com.rappidu.infra.mappers;

import br.com.rappidu.domain.entities.Item;
import br.com.rappidu.infra.persistence.entities.ItemEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemEntityMapper {

    ItemEntity toEntity(Item model);
    Item toModel(ItemEntity entity);
}
