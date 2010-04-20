package cesium.holder;

import org.w3c.css.sac.LexicalUnit;

/**
 * @project: Theme Builder for ExtJS 3.x
 * @Description:
 * @license: LGPL_v3
 * @author: Sergey Chentsov (extjs id: iv_ekker)
 * @mailto: sergchentsov@gmail.com
 * @version: 1.0.0
 * @Date: 17.08.2009
 * @Time: 14:03:33
 */
public class CSSPropertyHolderImpl extends AbstractResourcesHolder implements PropertyHolder{
    private LexicalUnit lexicalUnit;
    private boolean isImportant;
    private boolean isHeaderFontColor;
    private boolean isHeaderFontFamily;
    private boolean isHeaderFontWeight;
    private boolean isHeaderFontSize;
    private boolean isFontFamily;
    private boolean isFontWeight;
    private boolean isFontSize;
    private boolean isFormTriggerBorderStyle;
    private boolean isResizableOpacityCSSProperty;

    public boolean isFormTriggerBorderStyle() {
        return this.isFormTriggerBorderStyle;
    }

    public void setFormTriggerBorderStyle(boolean formTriggerBorderStyle) {
        this.isFormTriggerBorderStyle = formTriggerBorderStyle;
    }

    public boolean isFontFamily() {
        return isFontFamily;
    }

    public void setFontFamily(boolean fontFamily) {
        isFontFamily = fontFamily;
    }

    public boolean isFontWeight() {
        return isFontWeight;
    }

    public void setFontWeight(boolean fontWeight) {
        isFontWeight = fontWeight;
    }

    public boolean isFontSize() {
        return isFontSize;
    }

    public void setFontSize(boolean fontSize) {
        isFontSize = fontSize;
    }

    public boolean isHeaderFontSize() {
        return isHeaderFontSize;
    }

    public void setHeaderFontSize(boolean headerFontSize) {
        isHeaderFontSize = headerFontSize;
    }

    public boolean isHeaderFontWeight() {
        return isHeaderFontWeight;
    }

    public void setHeaderFontWeight(boolean headerFontWeight) {
        isHeaderFontWeight = headerFontWeight;
    }

    public boolean isHeaderFontFamily() {
        return this.isHeaderFontFamily;
    }

    public void setHeaderFontFamily(boolean headerFontFamily) {
        this.isHeaderFontFamily = headerFontFamily;
    }

    public boolean isHeaderFontColor() {
        return isHeaderFontColor;
    }

    public void setHeaderFontColor(boolean headerFontColor) {
        isHeaderFontColor = headerFontColor;
    }

    public LexicalUnit getLexicalUnit() {
        return lexicalUnit;
    }

    public void setLexicalUnit(LexicalUnit lexicalUnit) {
        this.lexicalUnit = lexicalUnit;
    }

    public boolean isImportant() {
        return isImportant;
    }

    public void setImportant(boolean important) {
        isImportant = important;
    }

    public CSSPropertyHolderImpl(LexicalUnit lexicalUnit, boolean isImportant
            , boolean isForeground, boolean isBackground, boolean isFontColor
            , boolean isBorderColor, boolean isHeaderFontColor, boolean isHeaderFontFamily, boolean isHeaderFontWeight, boolean isHeaderFontSize
            , boolean isFontFamily, boolean isFontWeight, boolean isFontSize, boolean isFormTriggerBorderStyle
            , boolean isProcessingNotNeeded, boolean isResizableOpacityCSSProperty) {
        this.lexicalUnit = lexicalUnit;
        this.isImportant = isImportant;
        this.setForeground(isForeground);
        this.setBackground(isBackground);
        this.setFontColor(isFontColor);
        this.setBorderColor(isBorderColor);
        this.isHeaderFontColor = isHeaderFontColor;
        this.isHeaderFontFamily = isHeaderFontFamily;
        this.isHeaderFontWeight = isHeaderFontWeight;
        this.isHeaderFontSize = isHeaderFontSize;
        this.isFontFamily = isFontFamily;
        this.isFontWeight = isFontWeight;
        this.isFontSize = isFontSize;
        this.isFormTriggerBorderStyle = isFormTriggerBorderStyle;
        this.isResizableOpacityCSSProperty = isResizableOpacityCSSProperty;
        setProcessingNotNeeded(isProcessingNotNeeded);
    }

    public CSSPropertyHolderImpl() {
    }

    public Object getContent() {
        return this.getLexicalUnit();
    }

    public void setContent(Object obj) {
        this.setLexicalUnit( (LexicalUnit) obj);
    }

    public String getPropertyStringValue() {
        String propValue = null;
        LexicalUnit lxUnit = (LexicalUnit) getContent();
        short lexicalUnitType = lxUnit.getLexicalUnitType();
        if (lexicalUnitType == LexicalUnit.SAC_URI){
            propValue = "url("+lxUnit.getStringValue()+"?rnd="+Math.random()+")"+(isImportant?" !important":"");
        } else{
            propValue = getCommonPropertyStringValue(lxUnit);
        }
        return propValue;
    }

    public String getPropertyStringValueForWeb(String thisControllerUrl, String resourcesPath) {
        String propValue = null;
        LexicalUnit lxUnit = (LexicalUnit) getContent();
        short lexicalUnitType = lxUnit.getLexicalUnitType();
        if (lexicalUnitType == LexicalUnit.SAC_URI){
            propValue = "url("+thisControllerUrl+"?resourcePath="
                    +resourcesPath+lxUnit.getStringValue().replaceFirst("..","")
                    +"&rnd="+Math.random()+")"+(isImportant?" !important":"");
        }else{
            propValue = getCommonPropertyStringValue(lxUnit);
        }
        return propValue;
    }

    public String getPropertyStringValueForZip(String newSchemaName) {
        String propValue = null;
        LexicalUnit lxUnit = (LexicalUnit) getContent();
        short lexicalUnitType = lxUnit.getLexicalUnitType();
        if (lexicalUnitType == LexicalUnit.SAC_URI){
            propValue = "url("+lxUnit.getStringValue().replaceFirst("default",newSchemaName)
                    +")"+(isImportant?" !important":"");
        }else{
            propValue = getCommonPropertyStringValue(lxUnit);
        }
        return propValue;
    }

    private String getCommonPropertyStringValue(LexicalUnit lxUnit) {
        String propValue = lxUnit.toString();
        short lexicalUnitType = lxUnit.getLexicalUnitType();
        if (lexicalUnitType == LexicalUnit.SAC_URI){
            propValue = "url("+lxUnit.getStringValue()+")"+(isImportant?" !important":"");
        }
        if (lexicalUnitType == LexicalUnit.SAC_RGBCOLOR){
            int oldIntegerValueR = lxUnit.getParameters().getIntegerValue();
            int oldIntegerValueG = lxUnit.getParameters().getNextLexicalUnit()
                    .getNextLexicalUnit().getIntegerValue();
            int oldIntegerValueB = lxUnit.getParameters().getNextLexicalUnit()
                    .getNextLexicalUnit().getNextLexicalUnit().getNextLexicalUnit()
                    .getIntegerValue();
            String r = Integer.toHexString(
                    oldIntegerValueR
            );
            String g = Integer.toHexString(
                    oldIntegerValueG
            );
            String b = Integer.toHexString(
                    oldIntegerValueB
            );
            propValue = new StringBuilder()
                    .append("#")
                    .append(r.length() == 1 ? ("0" + r) : r)
                    .append(g.length() == 1 ? ("0" + g) : g)
                    .append(b.length() == 1 ? ("0" + b) : b)
                    .append(isImportant ? " !important" : "").toString();
            //propValue = lxUnit.toString()+(isImportant?" !important":"");

        }
        if (lexicalUnitType == LexicalUnit.SAC_IDENT
                ||lexicalUnitType == LexicalUnit.SAC_STRING_VALUE){
            propValue = lxUnit.toString()+(isImportant?" !important":"");
        }
        if (lexicalUnitType == LexicalUnit.SAC_INTEGER){
            propValue = lxUnit.toString()+(isImportant?" !important":"");
        }
        if (lexicalUnitType == LexicalUnit.SAC_PIXEL
                ||lexicalUnitType == LexicalUnit.SAC_REAL){
            propValue = lxUnit.toString()+(isImportant?" !important":"");
        }

        return propValue;
    }

    public boolean isResizableOpacityCSSProperty() {
        return isResizableOpacityCSSProperty;
    }

    public void setResizableOpacityCSSProperty(boolean resizableOpacityCSSProperty) {
        isResizableOpacityCSSProperty = resizableOpacityCSSProperty;
    }
}
