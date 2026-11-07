/*
 * Decompiled with CFR 0.152.
 */
package age.of.civilizations2.jakowski.lukasz;

import age.of.civilizations2.jakowski.lukasz.Button;
import age.of.civilizations2.jakowski.lukasz.Button_Menu_Classic;
import age.of.civilizations2.jakowski.lukasz.ImageManager;
import age.of.civilizations2.jakowski.lukasz.Images;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Button_Menu_Classic_ReflectedCheckbox
extends Button_Menu_Classic {
    protected Button_Menu_Classic_ReflectedCheckbox(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable) {
        super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable);
    }

    protected Button_Menu_Classic_ReflectedCheckbox(String sText, int iTextPositionX, int iPosX, int iPosY, int iWidth, int iHeight, boolean isClickable, boolean checkboxState) {
        super(sText, iTextPositionX, iPosX, iPosY, iWidth, iHeight, isClickable, checkboxState);
    }

    @Override
    protected Button.Checkbox buildCheckbox() {
        if (this.checkbox) {
            return new Button.Checkbox(){

                @Override
                public void drawCheckBox(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean scrollableY) {
                    if (Button_Menu_Classic_ReflectedCheckbox.this.getCheckboxState()) {
                        oSB.setColor(new Color(0.55f, 0.8f, 0.0f, 0.25f));
                    } else {
                        oSB.setColor(new Color(0.8f, 0.137f, 0.0f, 0.25f));
                    }
                    ImageManager.getImage(Images.slider_gradient).draw(oSB, Button_Menu_Classic_ReflectedCheckbox.this.getPosX() + Button_Menu_Classic_ReflectedCheckbox.this.getWidth() - Button_Menu_Classic_ReflectedCheckbox.this.getWidth() / 4 + iTranslateX, Button_Menu_Classic_ReflectedCheckbox.this.getPosY() - ImageManager.getImage(Images.slider_gradient).getHeight() + 1 + iTranslateY, Button_Menu_Classic_ReflectedCheckbox.this.getWidth() / 4, Button_Menu_Classic_ReflectedCheckbox.this.getHeight() - 2, true, false);
                    oSB.setColor(Color.WHITE);
                }
            };
        }
        return new Button.Checkbox(){

            @Override
            public void drawCheckBox(SpriteBatch oSB, int iTranslateX, int iTranslateY, boolean scrollableY) {
            }
        };
    }
}

