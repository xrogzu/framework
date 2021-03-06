/*
 *
 *  * Copyright 2016 the original author or authors.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */
package tests.cp.mock;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import leap.lang.jdbc.StatementAdapter;

public class MockStatement extends StatementAdapter {

	private final MockConnection conn;
	
	private String lastQuery; 

	public MockStatement(MockConnection connection) {
		this.conn = connection;
        conn.increaseOpeningStatement();
	}
	
	public String getLastQuery() {
		return lastQuery;
	}

	@Override
    public Connection getConnection() throws SQLException {
		return conn;
	}

	@Override
    public ResultSet executeQuery(String sql) throws SQLException {
		this.lastQuery = sql;
		return new MockResultSet(conn);
    }

    @Override
    public void close() throws SQLException {
        conn.decreaseOpeningStatement();
        super.close();
    }
	
}
