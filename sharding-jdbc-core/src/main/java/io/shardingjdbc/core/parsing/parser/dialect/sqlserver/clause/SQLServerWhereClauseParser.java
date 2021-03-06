/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package io.shardingjdbc.core.parsing.parser.dialect.sqlserver.clause;

import io.shardingjdbc.core.constant.DatabaseType;
import io.shardingjdbc.core.parsing.lexer.LexerEngine;
import io.shardingjdbc.core.parsing.parser.context.selectitem.SelectItem;
import io.shardingjdbc.core.parsing.parser.clause.WhereClauseParser;
import com.google.common.base.Optional;

import java.util.List;

/**
 * Where clause parser for SQLServer.
 *
 * @author zhangliang
 */
public final class SQLServerWhereClauseParser extends WhereClauseParser {
    
    public SQLServerWhereClauseParser(final LexerEngine lexerEngine) {
        super(DatabaseType.SQLServer, lexerEngine);
    }
    
    @Override
    protected boolean isRowNumberCondition(final List<SelectItem> items, final String columnLabel) {
        Optional<String> rowNumberAlias = Optional.absent();
        for (SelectItem each : items) {
            if (each.getAlias().isPresent() && "ROW_NUMBER".equalsIgnoreCase(each.getExpression())) {
                rowNumberAlias = each.getAlias();
                break;
            }
        }
        return columnLabel.equalsIgnoreCase(rowNumberAlias.orNull());
    }
}
