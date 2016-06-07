/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tested.beans;

import leap.core.annotation.Monitored;
import leap.lang.Threads;

public class TMonitorBean {

    public void test(int i, String s) {
        Threads.sleep(2);
        test1();
    }

    @Monitored
    protected void test1() {
        Threads.sleep(2);
        throw new RuntimeException("err");
    }

    public void root() {
        Threads.sleep(10);
        nest1(10);
        nest1(12);
        nest2(15);
    }

    public void nest1(long sleep) {
        Threads.sleep(sleep);
        nest2(sleep);
    }

    public void nest2(long sleep) {
        Threads.sleep(sleep);
    }

}