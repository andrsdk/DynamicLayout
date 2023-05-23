/*
 * Copyright 2019 Flipkart Internet Pvt. Ltd.
 *
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
 */

package freelifer.android.dl.framework;

import java.util.Map;

import freelifer.android.dl.framework.parser.ViewTypeParser;

/**
 * ProteusResources
 *
 * @author adityasharat
 */

public class DViewResources {

    private final Map<String, ViewTypeParser> parsers;

    DViewResources(Map<String, ViewTypeParser> parsers) {
        this.parsers = parsers;
    }

    public Map<String, ViewTypeParser> getParsers() {
        return parsers;
    }

}
