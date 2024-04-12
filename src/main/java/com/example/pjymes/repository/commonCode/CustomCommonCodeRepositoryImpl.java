package com.example.pjymes.repository.commonCode;

import com.example.pjymes.domain.CommonCode;
import com.example.pjymes.domain.QCommonCode;
import com.example.pjymes.domain.QMenu;
import com.example.pjymes.dto.CommonCodeDTO;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CustomCommonCodeRepositoryImpl implements CustomCommonCodeRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<CommonCode> majorCodeSearch() {
        QCommonCode qCommonCode = QCommonCode.commonCode;

        return jpaQueryFactory
                .select(qCommonCode)
                .from(qCommonCode)
                .where(qCommonCode.subCode.eq("00"))
                .fetch();
    }

    @Override
    public List<CommonCode> subCodeSearch(CommonCodeDTO commonCodeDTO) {
        QCommonCode qCommonCode = QCommonCode.commonCode;

        return jpaQueryFactory
                .select(qCommonCode)
                .from(qCommonCode)
                .where(qCommonCode.majorCode.eq(commonCodeDTO.getMajorCode())
                        .and(qCommonCode.subCode.ne("00")))
                .fetch();
    }

    @Override
    public Long majorCodeCountSearch(CommonCodeDTO commonCodeDTO) {
        QCommonCode qCommonCode = QCommonCode.commonCode;

        return jpaQueryFactory
                .select(qCommonCode.count().add(1L))
                .from(qCommonCode)
                .where(qCommonCode.subCode.eq("00"))
                .fetchFirst();
    }

    @Override
    public Long subCodeCountSearch(CommonCodeDTO commonCodeDTO) {
        QCommonCode qCommonCode = QCommonCode.commonCode;

        return jpaQueryFactory
                .select(qCommonCode.count())
                .from(qCommonCode)
                .where(qCommonCode.majorCode.eq(commonCodeDTO.getMajorCode()))
                .fetchFirst();
    }
}
