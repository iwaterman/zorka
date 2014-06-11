/**
 * Copyright 2012-2014 Rafal Lewczuk <rafal.lewczuk@jitlogic.com>
 *
 * ZORKA is free software. You can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * ZORKA is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * ZORKA. If not, see <http://www.gnu.org/licenses/>.
 */

package com.jitlogic.zorka.core.test.agent;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.jitlogic.zorka.core.integ.ZabbixLib;
import com.jitlogic.zorka.core.test.support.ZorkaFixture;

public class BshAgentSimpleUnitTest extends ZorkaFixture {

    @Before
    public void setUp() throws Exception {
        mBeanServerRegistry.register("java", java.lang.management.ManagementFactory.getPlatformMBeanServer(), null);
        ZabbixLib zl = new ZabbixLib(mBeanServerRegistry, config);
        zorkaAgent.put("zabbix", zl);
        
        String msg = zorkaAgent.loadScript(getClass().getResource("/unittest/BshAgentTest.bsh").getPath());
        System.out.println(msg);
    }


    @Test
    public void testTrivialQuery() throws Exception {
        assertEquals("5", zorkaAgent.query("2+3"));
    }
}
