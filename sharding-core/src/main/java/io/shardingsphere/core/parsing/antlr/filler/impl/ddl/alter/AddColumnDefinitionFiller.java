/*
 * Copyright 2016-2018 shardingsphere.io.
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

package io.shardingsphere.core.parsing.antlr.filler.impl.ddl.alter;

import io.shardingsphere.core.metadata.table.ShardingTableMetaData;
import io.shardingsphere.core.parsing.antlr.filler.SQLStatementFiller;
import io.shardingsphere.core.parsing.antlr.sql.segment.definition.column.alter.AddColumnDefinitionSegment;
import io.shardingsphere.core.parsing.antlr.sql.statement.ddl.AlterTableStatement;
import io.shardingsphere.core.parsing.parser.sql.SQLStatement;
import io.shardingsphere.core.rule.ShardingRule;

/**
 * Add column definition filler.
 *
 * @author duhongjun
 */
public final class AddColumnDefinitionFiller implements SQLStatementFiller<AddColumnDefinitionSegment> {
    
    @Override
    public void fill(final AddColumnDefinitionSegment sqlSegment, 
                     final SQLStatement sqlStatement, final String sql, final ShardingRule shardingRule, final ShardingTableMetaData shardingTableMetaData) {
        AlterTableStatement alterTableStatement = (AlterTableStatement) sqlStatement;
        if (!alterTableStatement.findColumnDefinitionFromMetaData(sqlSegment.getColumnDefinition().getColumnName(), shardingTableMetaData).isPresent()) {
            alterTableStatement.getAddedColumnDefinitions().add(sqlSegment.getColumnDefinition());
        }
        if (sqlSegment.getColumnPosition().isPresent()) {
            alterTableStatement.getChangedPositionColumns().add(sqlSegment.getColumnPosition().get());
        }
    }
}
