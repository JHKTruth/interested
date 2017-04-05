/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jhk.pulsing.db.cassandra.chat;

import java.util.UUID;

import org.jhk.pulsing.db.cassandra.ICassandraTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.DataType;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.schemabuilder.SchemaBuilder;
import com.datastax.driver.core.schemabuilder.SchemaStatement;

/**
 * @author Ji Kim
 */
public final class ChatMessageTable implements ICassandraTable {
    
    private static final Logger _LOGGER = LoggerFactory.getLogger(ChatMessageTable.class);
    
    private static final String _CHAT_MESSAGE_TABLE = "CHAT_MESSAGE_TABLE";
    private static final String _CHAT_MESSAGE_VIEW_COUNT_TABLE = "CHAT_MESSAGE_VIEW_COUNT_TABLE";
    
    private final PreparedStatement _CHAT_MESSAGE_QUERY;
    private final PreparedStatement _CHAT_MESSAGE_INSERT;
    
    private final PreparedStatement _CHAT_MESSAGE_VIEW_COUNT_QUERY;
    private final PreparedStatement _CHAT_MESSAGE_VIEW_COUNT_INSERT;
    
    private final Session _session;
    private final String _keySpace;
    
    public ChatMessageTable(Session session, String keySpace) {
        super();
        
        _session = session;
        _keySpace = keySpace;
        
        SchemaStatement cMSchemaStatement = SchemaBuilder.createTable(_CHAT_MESSAGE_TABLE)
                .ifNotExists()
                .addPartitionKey("chat_lobby_id", DataType.timeuuid())
                .addClusteringColumn("timestamp", DataType.bigint())
                .addColumn("msg_id", DataType.uuid())
                .addColumn("user_id", DataType.bigint())
                .addColumn("message", DataType.text())
                .withOptions().clusteringOrder("timestamp", SchemaBuilder.Direction.DESC);
        
        _session.execute(cMSchemaStatement);
        
        SchemaStatement cmvCSchemaStatement = SchemaBuilder.createTable(_CHAT_MESSAGE_VIEW_COUNT_TABLE)
                .ifNotExists()
                .addPartitionKey("msg_id", DataType.uuid())
                .addClusteringColumn("user_id", DataType.bigint())
                .addColumn("timestamp", DataType.bigint())
                .withOptions().clusteringOrder("user_id", SchemaBuilder.Direction.DESC);
        
        _session.execute(cmvCSchemaStatement);
        
        _CHAT_MESSAGE_QUERY = _session.prepare("SELECT * FROM " + _CHAT_MESSAGE_TABLE + 
                " WHERE chat_lobby_id=? AND timestamp < ? LIMIT 40");
        _CHAT_MESSAGE_INSERT = _session.prepare("INSERT INTO " + _CHAT_MESSAGE_TABLE + 
                " (chat_lobby_id, user_id, msg_id, timestamp, message) VALUES (?, ?, ?, ?, ?)");
        
        _CHAT_MESSAGE_VIEW_COUNT_QUERY = _session.prepare("SELECT * FROM " + _CHAT_MESSAGE_VIEW_COUNT_TABLE + 
                " WHERE msg_id=?");
        _CHAT_MESSAGE_VIEW_COUNT_INSERT = _session.prepare("INSERT INTO " + _CHAT_MESSAGE_VIEW_COUNT_TABLE + 
                " (msg_id, timestamp, user_id) VALUES (?, ?, ?)");
    }
    
    public void messageInsert(UUID cLId, long from, long timeStamp, String message) {
        _LOGGER.info("ChatMessageTable.messageInsert : " + cLId + " - " + ";" + message);
        
        UUID msgId = UUID.randomUUID();
        BoundStatement cLMInsert = _CHAT_MESSAGE_INSERT.bind(cLId, from, msgId, timeStamp, message);
        _session.executeAsync(cLMInsert);
        
        messageViewCountInsert(msgId, timeStamp, from);
    }
    
    /**
     * TODO: Use Spark to query CHAT_MESSAGE_VIEW_COUNT_TABLE and map the count in
     * 
     * @param cLId
     * @param timeStamp
     * @return
     */
    public ResultSet messageQuery(UUID cLId, Long timeStamp) {
        _LOGGER.info("ChatMessageTable.messageQuery : " + cLId + " - " + timeStamp);
        
        BoundStatement cLMQuery = _CHAT_MESSAGE_QUERY.bind(cLId, timeStamp);
        return _session.execute(cLMQuery);
    }
    
    public void messageViewCountInsert(UUID msgId, long timeStamp, long userId) {
        _LOGGER.info("ChatMessageTable.messageViewCountInsert : " + msgId + " - " + userId + "/" + timeStamp);
        
        _session.executeAsync(_CHAT_MESSAGE_VIEW_COUNT_INSERT.bind(msgId, timeStamp, userId));
    }
    
    private ResultSet messageViewCountQuery(UUID msgId) {
        _LOGGER.info("ChatMessageTable.messageViewCountQuery : " + msgId);
        
        return _session.execute(_CHAT_MESSAGE_VIEW_COUNT_QUERY.bind(msgId));
    }

    @Override
    public void destroy() {
        _session.execute(SchemaBuilder.dropTable(_CHAT_MESSAGE_TABLE));
        _session.execute(SchemaBuilder.dropTable(_CHAT_MESSAGE_VIEW_COUNT_TABLE));
    }

}
