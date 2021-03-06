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

import java.sql.ResultSet;
import java.sql.SQLException;

import leap.lang.jdbc.DatabaseMetaDataAdapter;

public class MockDatabaseMetaData extends DatabaseMetaDataAdapter {
	
	private final MockDataSource dataSource;
	private final MockConnection connection;
	
	public MockDatabaseMetaData(MockConnection connection) {
		this.connection = connection;
		this.dataSource = connection.getDataSource();
	}
	
	public MockDataSource getDataSource() {
		return dataSource;
	}
	
	public MockConnection getConnection() {
		return connection;
	}

	@Override
    public String getURL() throws SQLException {
		return dataSource.getUrl();
	}
	
	@Override
    public String getUserName() throws SQLException {
		return "mock";
    }

	@Override
    public String getDatabaseProductName() throws SQLException {
		return "MySQL";
	}

    @Override
    public ResultSet getPrimaryKeys(String catalog, String schema, String table) throws SQLException {
        return new MockResultSet(connection);
    }
}
