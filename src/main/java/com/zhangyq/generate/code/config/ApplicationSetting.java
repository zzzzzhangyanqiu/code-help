package com.zhangyq.generate.code.config;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.zhangyq.generate.code.generator.file.TestFreemarkerConfiguration;
import com.zhangyq.generate.util.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(name = "codeHelper", storages = {@Storage(value = "codeHelper.xml")})
public class ApplicationSetting implements PersistentStateComponent<ApplicationSetting> {

    TestFreemarkerConfiguration testFreemarkerConfiguration = new TestFreemarkerConfiguration();

    public static ApplicationSetting getInstance() {
        return ApplicationManager.getApplication().getService(ApplicationSetting.class);
    }

    public String testUtilTemplate;
    public String methodTemplate;
    public String classTemplate;

    @Override
    public @Nullable ApplicationSetting getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull ApplicationSetting state) {
        testUtilTemplate = state.testUtilTemplate;
        methodTemplate = state.methodTemplate;
        classTemplate = state.classTemplate;

        setDefault();
    }

    public void setDefault() {
        if(StringUtils.isEmpty(testUtilTemplate)){
            testUtilTemplate = FileUtil.getTemplateString("TestUtils.ftl", testFreemarkerConfiguration);
        }

        if(StringUtils.isEmpty(classTemplate)){
            classTemplate = FileUtil.getTemplateString("TestClass.ftl", testFreemarkerConfiguration);
        }

        if(StringUtils.isEmpty(methodTemplate)){
            methodTemplate = FileUtil.getTemplateString("TestMethod.ftl", testFreemarkerConfiguration);
        }
    }

    public void reset() {
        testUtilTemplate = StringUtils.EMPTY;
        classTemplate = StringUtils.EMPTY;
        methodTemplate = StringUtils.EMPTY;
        setDefault();
    }
}
