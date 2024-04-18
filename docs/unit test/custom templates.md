### Custom Templates
Code generation is implemented using [Apache FreeMarker] (https://freemarker.apache.org/) and supports custom templates at

the method/class level, but the generated file name cannot be changed

you can custom templates in Settings | Other Settings | CodeHelper


Here are some of the variables you'll use when custom templates


### Variables

#### Method

| name             | remarks                            | type   |
| ---------------- | ---------------------------------- | ------ |
| ${jsonFilePath}  | generated json file path           | String |
| ${jsonFileName}  | generated json file name           | String |
| ${methodName}    | name of target method              | String |
| ${methodContent} | unit test code generated in method | String |


#### Class

| name               | remarks                       | type               |
| ------------------ | ----------------------------- | ------------------ |
| ${packageName}     | package name of current class | String             |
| ${needImports}     | collection of need import     | Collection&lt;String&gt; |
| ${baseTestClass}   | BaseTest class name of selected，default empty | String |
| ${targetClassName} | name of target class | String |
| ${needMockFields}  | collection of fields that need mock， | Collection&lt;Object&gt; |
| ${needMockFields.presentableText} | the type of the field                          | String                   |
| ${needMockFields.name} | the name of the field | String |
| ${methodList} | collection of unit test code generated | Collection&lt;String&gt; |

#### Utils

nothing
