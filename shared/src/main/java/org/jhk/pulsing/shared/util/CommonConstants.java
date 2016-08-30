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
package org.jhk.pulsing.shared.util;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Ji Kim
 */
public final class CommonConstants {
    
    public static final String DEFAULT_BOOTSTRAP_HOST;
    public static final int DEFAULT_BOOTSTRAP_PORT;
    public static final int DEFAULT_STORM_TICK_TUPLE_FREQ_SECONDS;
    public static final int DEFAULT_STORM_INTERVAL_SECONDS;
    
    public static final String CASSANDRA_CONTACT_POINT;
    
    public static final String TIME_INTERVAL_ID_VALUE_DELIM = ":";
    public static final String TIME_INTERVAL_PERSIST_TIMESTAMP_DELIM = "/";
    
    public static final int HASH_CODE_INIT_VALUE = 3;
    public static final int HASH_CODE_MULTIPLY_VALUE = 31;
    
    public enum TOPICS {
		PULSE_SUBSCRIBE, USER_CREATE, PULSE_CREATE;
	};
	
	static {
        
        Properties props = new Properties();
        
        try {
            props.load(RedisConstants.class.getResourceAsStream("common.properties"));
            
            DEFAULT_BOOTSTRAP_HOST = props.getProperty("bootstrap_host");
            DEFAULT_BOOTSTRAP_PORT = Integer.parseInt(props.getProperty("bootstrap_port"));
            DEFAULT_STORM_TICK_TUPLE_FREQ_SECONDS = Integer.parseInt(props.getProperty("default_storm_tick_tuple_freq_seconds"));
            DEFAULT_STORM_INTERVAL_SECONDS = Integer.parseInt(props.getProperty("default_storm_interval_seconds"));
            
            CASSANDRA_CONTACT_POINT = props.getProperty("cassandra_contact_point");
        } catch (IOException ioExcept) {
            throw new RuntimeException("Error while reading common.properties", ioExcept);
        }
    }
    
    private CommonConstants() {
        super();
    }
    
}
