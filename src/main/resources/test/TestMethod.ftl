    @ParameterizedTest
    @ValueSource(strings = {"/${jsonFilePath}/${jsonFileName}"})
    public void ${methodName}Test(String str) {
        JSONObject arg = TestUtils.getTestArg(str);
        ${methodContent}
    }