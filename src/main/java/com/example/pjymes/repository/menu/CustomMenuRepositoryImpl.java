package com.example.pjymes.repository.menu;

import com.example.pjymes.domain.QMenu;
import com.example.pjymes.dto.MenuDTO;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomMenuRepositoryImpl implements CustomMenuRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public Long orderSearch(MenuDTO menuDTO) {
        QMenu qMenu = QMenu.menu;

        BooleanExpression whereClause = qMenu.parentMenu.isNull();
        if (menuDTO.getParentMenu() != null) {
            whereClause = qMenu.parentMenu.menuId.eq(menuDTO.getParentMenu().getMenuId());
        }

        return jpaQueryFactory
                .select(qMenu.count().add(1L))
                .from(qMenu)
                .where(whereClause)
                .fetchFirst();
    }
}
