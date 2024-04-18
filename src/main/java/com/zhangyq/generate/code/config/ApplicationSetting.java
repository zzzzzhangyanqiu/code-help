package com.zhangyq.generate.code.config;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.zhangyq.generate.code.dialog.config.ApplicationSettingPanel;
import com.zhangyq.generate.code.generator.file.TestFreemarkerConfiguration;
import com.zhangyq.generate.util.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(name = "codeHelper", storages = {@Storage(value = "codeHelper.xml")})
public class ApplicationSetting implements PersistentStateComponent<ApplicationSetting> {

    private static final TestFreemarkerConfiguration testFreemarkerConfiguration;
    private static final String DEFAULT_TEST_UTILS_TEMPLATE;
    private static final String DEFAULT_TEST_CLASS_TEMPLATE;
    private static final String DEFAULT_TEST_METHOD_TEMPLATE;

    static {
        testFreemarkerConfiguration = new TestFreemarkerConfiguration();
        DEFAULT_TEST_UTILS_TEMPLATE = FileUtil.getTemplateString("TestUtils.ftl", testFreemarkerConfiguration);
        DEFAULT_TEST_CLASS_TEMPLATE = FileUtil.getTemplateString("TestClass.ftl", testFreemarkerConfiguration);
        DEFAULT_TEST_METHOD_TEMPLATE = FileUtil.getTemplateString("TestMethod.ftl", testFreemarkerConfiguration);
    }


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
            testUtilTemplate = DEFAULT_TEST_UTILS_TEMPLATE;
        }

        if(StringUtils.isEmpty(classTemplate)){
            classTemplate = DEFAULT_TEST_CLASS_TEMPLATE;
        }

        if(StringUtils.isEmpty(methodTemplate)){
            methodTemplate = DEFAULT_TEST_METHOD_TEMPLATE;
        }
    }

    public void reset() {
        if(!DEFAULT_TEST_CLASS_TEMPLATE.equals(classTemplate)) {
            classTemplate = DEFAULT_TEST_CLASS_TEMPLATE;
        }

        if(!DEFAULT_TEST_UTILS_TEMPLATE.equals(testUtilTemplate)) {
            testUtilTemplate = DEFAULT_TEST_UTILS_TEMPLATE;
        }

        if(!DEFAULT_TEST_METHOD_TEMPLATE.equals(methodTemplate)) {
            methodTemplate = DEFAULT_TEST_METHOD_TEMPLATE;
        }
    }

    public boolean isDefault(ApplicationSettingPanel applicationSettingPanel) {
        String methodTemplateText = applicationSettingPanel.methodTemplate.getText();
        String classTemplateText = applicationSettingPanel.classTemplate.getText();
        String testUtilTemplateText = applicationSettingPanel.testUtilTemplate.getText();
        return DEFAULT_TEST_UTILS_TEMPLATE.equals(testUtilTemplateText)
                && DEFAULT_TEST_CLASS_TEMPLATE.equals(classTemplateText)
                && DEFAULT_TEST_METHOD_TEMPLATE.equals(methodTemplateText);
    }
}
