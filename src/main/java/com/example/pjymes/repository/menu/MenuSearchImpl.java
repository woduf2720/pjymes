package com.example.pjymes.repository.menu;

import com.example.pjymes.domain.Menu;
import com.example.pjymes.domain.QMenu;
import com.example.pjymes.dto.MenuDTO;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MenuSearchImpl implements MenuSearch{

    private final JPAQueryFactory jpaQueryFactory;

    public Long orderSearch(MenuDTO menuDTO) {
        QMenu qMenu = QMenu.menu;

        BooleanExpression whereClause = qMenu.parentMenu.isNull();
        Long parentId = menuDTO.getParent().getMenuId();
        if (parentId != null) {
            whereClause = qMenu.parentMenu.menuId.eq(parentId);
        }

        return jpaQueryFactory
                .select(qMenu.count().add(1L))
                .from(qMenu)
                .where(whereClause)
                .fetchFirst();
    }
}
