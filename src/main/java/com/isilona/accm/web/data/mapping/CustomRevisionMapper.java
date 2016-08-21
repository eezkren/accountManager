package com.isilona.accm.web.data.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.isilona.accm.db.model.CustomRevision;
import com.isilona.accm.web.data.response.CustomRevisionDto;

/**
 * @author iivanov
 *
 */

@Mapper(uses = { RevisionDateMapper.class }, componentModel = "spring")
public interface CustomRevisionMapper {

    @Mapping(source = "timestamp", target = "revisionDate")
    CustomRevisionDto customRevisionToCustomRevisionDto(CustomRevision customRevision);

}
