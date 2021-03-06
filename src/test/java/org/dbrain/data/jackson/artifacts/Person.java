/*
 * Copyright [2015] [Eric Poitras]
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package org.dbrain.data.jackson.artifacts;

import org.dbrain.data.Fqn;
import org.dbrain.data.Path;

/**
 * Created by epoitras on 9/4/15.
 */
public class Person {

    Fqn id = Fqn.of( "id" );
    String name;
    String lastName;
    Person friend;
    Path address = Path.of( "address" );

    public Person() {
    }

    public Person( String name, String lastName ) {
        this.name = name;
        this.lastName = lastName;
    }

    public Fqn getId() {
        return id;
    }

    public void setId( Fqn id ) {
        this.id = id;
    }

    public Person getFriend() {
        return friend;
    }

    public void setFriend( Person friend ) {
        this.friend = friend;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }

    public Path getAddress() {
        return address;
    }

    public void setAddress( Path address ) {
        this.address = address;
    }

}
