package io.github.cottonmc.cotton.gui.widget;

import java.util.function.Consumer;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import org.lwjgl.glfw.GLFW;

import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;

public class WTextField extends WWidget {
	public static final int OFFSET_X_TEXT = 4;
	//public static final int OFFSET_Y_TEXT = 6;
	
	protected String text = "";
	protected int maxLength = 16;
	protected boolean editable = true;
	
	protected int enabledColor = 0xE0E0E0;
	protected int uneditableColor = 0x707070;
	
	@Nullable
	protected String suggestion = null;
	
	protected int cursor = 0;
	
	protected Consumer<String> onChanged;
	
	protected Predicate<String> textPredicate;
	
	@Environment(EnvType.CLIENT)
	@Nullable
	protected BackgroundPainter backgroundPainter;

	public WTextField() {
	}
	
	public WTextField(Text suggestion) {
		this.suggestion = suggestion.asString();
	}
	
	public void setText(String s) {
		if (this.textPredicate.test(s)) {
			this.text = (s.length()>maxLength) ? s.substring(0,maxLength) : s;
			if (onChanged!=null) onChanged.accept(this.text);
		}
	}

	public String getText() {
		return this.text;
	}
	
	@Override
	public boolean canResize() {
		return true;
	}
	
	/*
	public String getSelectedText() {
		int start = this.cursorMax < this.cursorMin ? this.cursorMax : this.cursorMin;
		int end = this.cursorMax < this.cursorMin ? this.cursorMin : this.cursorMax;
		return this.text.substring(start, end);
	}*/

	/*
	//This would seem to insert or paste text in the vanilla control
	public void addText(String string_1) {
		String string_2 = "";
		String string_3 = SharedConstants.stripInvalidChars(string_1);
		int int_1 = this.cursorMax < this.cursorMin ? this.cursorMax : this.cursorMin;
		int int_2 = this.cursorMax < this.cursorMin ? this.cursorMin : this.cursorMax;
		int int_3 = this.maxLength - this.text.length() - (int_1 - int_2);
		if (!this.text.isEmpty()) {
			string_2 = string_2 + this.text.substring(0, int_1);
		}

		int int_5;
		if (int_3 < string_3.length()) {
			string_2 = string_2 + string_3.substring(0, int_3);
			int_5 = int_3;
		} else {
			string_2 = string_2 + string_3;
			int_5 = string_3.length();
		}

		if (!this.text.isEmpty() && int_2 < this.text.length()) {
			string_2 = string_2 + this.text.substring(int_2);
		}

		if (this.textPredicate.test(string_2)) {
			this.text = string_2;
			this.setCursor(int_1 + int_5);
			this.method_1884(this.cursorMax);
			this.onChanged(this.text);
		}
	}*/
	
	/*

	//this would seem to delete text. if int_1 is negative, deletes from the end?
	public void method_1878(int int_1) {
		if (!this.text.isEmpty()) {
			if (this.cursorMin != this.cursorMax) {
				this.addText("");
			} else {
				boolean boolean_1 = int_1 < 0;
				int int_2 = boolean_1 ? this.cursorMax + int_1 : this.cursorMax;
				int int_3 = boolean_1 ? this.cursorMax : this.cursorMax + int_1;
				String string_1 = "";
				if (int_2 >= 0) {
					string_1 = this.text.substring(0, int_2);
				}

				if (int_3 < this.text.length()) {
					string_1 = string_1 + this.text.substring(int_3);
				}

				if (this.textPredicate.test(string_1)) {
					this.text = string_1;
					if (boolean_1) {
						this.moveCursor(int_1);
					}

					this.onChanged(this.text);
				}
			}
		}
	}*/

	public void setCursorPos(int location) {
		this.cursor = MathHelper.clamp(location, 0, this.text.length());
	}

	public int getMaxLength() {
		return this.maxLength;
	}

	public int getCursor() {
		return this.cursor;
	}
	
	public boolean isEditable() {
		return this.editable;
	}
	
	/*
	public boolean keyPressed(int int_1, int int_2, int int_3) {
		if (!this.method_20315()) {
			return false;
		} else {
			this.field_17037 = Screen.hasShiftDown();
			if (Screen.isSelectAll(int_1)) {
				this.method_1872();
				this.method_1884(0);
				return true;
			} else if (Screen.isCopy(int_1)) {
				MinecraftClient.getInstance().keyboard.setClipboard(this.getSelectedText());
				return true;
			} else if (Screen.isPaste(int_1)) {
				if (this.editable) {
					this.addText(MinecraftClient.getInstance().keyboard.getClipboard());
				}

				return true;
			} else if (Screen.isCut(int_1)) {
				MinecraftClient.getInstance().keyboard.setClipboard(this.getSelectedText());
				if (this.editable) {
					this.addText("");
				}

				return true;
			} else {
				switch(int_1) {
				case 259:
					if (this.editable) {
						this.method_16873(-1);
					}

					return true;
				case 260:
				case 264:
				case 265:
				case 266:
				case 267:
				default:
					return false;
				case 261:
					if (this.editable) {
						this.method_16873(1);
					}

					return true;
				case 262:
					if (Screen.hasControlDown()) {
						this.method_1883(this.method_1853(1));
					} else {
						this.moveCursor(1);
					}

					return true;
				case 263:
					if (Screen.hasControlDown()) {
						this.method_1883(this.method_1853(-1));
					} else {
						this.moveCursor(-1);
					}

					return true;
				case 268:
					this.method_1870();
					return true;
				case 269:
					this.method_1872();
					return true;
				}
			}
		}
	}*/
	
	/*
	public boolean charTyped(char char_1, int int_1) {
		if (!this.method_20315()) {
			return false;
		} else if (SharedConstants.isValidChar(char_1)) {
			if (this.editable) {
				this.addText(Character.toString(char_1));
			}

			return true;
		} else {
			return false;
		}
	}*/
	/*
	@Override
	public void onClick(int x, int y, int button) {
		
	}
	
	public boolean mouseClicked(double double_1, double double_2, int int_1) {
		if (!this.isVisible()) {
			return false;
		} else {
			boolean boolean_1 = double_1 >= (double)this.x && double_1 < (double)(this.x + this.width) && double_2 >= (double)this.y && double_2 < (double)(this.y + this.height);
			if (this.field_2096) {
				this.method_1876(boolean_1);
			}

			if (this.isFocused() && boolean_1 && int_1 == 0) {
				int int_2 = MathHelper.floor(double_1) - this.x;
				if (this.focused) {
					int_2 -= 4;
				}

				String string_1 = this.textRenderer.trimToWidth(this.text.substring(this.field_2103), this.method_1859());
				this.method_1883(this.textRenderer.trimToWidth(string_1, int_2).length() + this.field_2103);
				return true;
			} else {
				return false;
			}
		}
	}*/

	
	public void renderButton(int x, int y) {
		//if (this.focused) { //has border?
			ScreenDrawing.rect(x-1, y-1, width+2, height+2, 0xFFA0A0A0);
			ScreenDrawing.rect(x, y, width, height, 0xFF000000);
		//}

		int textColor = this.editable ? this.enabledColor : this.uneditableColor;
		//int int_4 = this.cursorMax - this.field_2103;
		int adjustedCursor = this.cursor;// - this.field_2103;
		String trimText = MinecraftClient.getInstance().textRenderer.trimToWidth(this.text, this.width);
		//boolean boolean_1 = int_4 >= 0 && int_4 <= string_1.length();
		boolean focused = this.isFocused(); //this.isFocused() && this.focusedTicks / 6 % 2 == 0 && boolean_1; //Blinks the cursor
		int textX = x + OFFSET_X_TEXT;
		int textY = y + (height - 8) / 2;
		int int_8 = textX;
		if (adjustedCursor > trimText.length()) {
			adjustedCursor = trimText.length();
		}

		if (!trimText.isEmpty()) {
			String string_2 = trimText.substring(0,adjustedCursor);
			int_8 = MinecraftClient.getInstance().textRenderer.drawWithShadow(string_2, (float)textX, (float)textY, textColor);
		}

		boolean boolean_3 = adjustedCursor < trimText.length(); //false; //this.cursorMax < this.text.length() || this.text.length() >= this.getMaxLength();
		
		/*if (!boolean_1) {
			int_9 = int_4 > 0 ? int_6 + this.width : int_6;
		} else if (boolean_3) {
			int_9 = int_8 - 1;
			--int_8;
		}*/

		//if (!trimText.isEmpty() && boolean_1 && int_4 < trimText.length()) {
		if (adjustedCursor<trimText.length()) {
			MinecraftClient.getInstance().textRenderer.drawWithShadow(trimText.substring(adjustedCursor), (float)int_8-1, (float)textY, textColor);
		}
			

		if (!boolean_3 && this.suggestion != null) {
			MinecraftClient.getInstance().textRenderer.drawWithShadow(this.suggestion, (float)(int_8 - 1), textY, -8355712);
		}

		//int var10002;
		//int var10003;
		if (focused) {
			if (adjustedCursor<trimText.length()) {
				int caretLoc = WTextField.getCaretOffset(text, cursor);
				ScreenDrawing.rect(textX+caretLoc-1, textY-2, 1, 12, 0xFFD0D0D0);
			//if (boolean_3) {
			//	int var10001 = int_7 - 1;
			//	var10002 = int_9 + 1;
			//	var10003 = int_7 + 1;
			//	
			//	DrawableHelper.fill(int_9, var10001, var10002, var10003 + 9, -3092272);
				
			} else {
				MinecraftClient.getInstance().textRenderer.drawWithShadow("_", (float)int_8, (float)textY, textColor);
			}
		}

		//if (adjustedCursor != int_4) {
		//	int int_10 = int_6 + MinecraftClient.getInstance().textRenderer.getStringWidth(trimText.substring(0, adjustedCursor));
		//	var10002 = int_7 - 1;
		//	var10003 = int_10 - 1;
		//	int var10004 = int_7 + 1;
		//	//this.method_1886(int_9, var10002, var10003, var10004 + 9);
		//}
	}

	/*
	private void method_1886(int int_1, int int_2, int int_3, int int_4) {
		int int_6;
		if (int_1 < int_3) {
			int_6 = int_1;
			int_1 = int_3;
			int_3 = int_6;
		}

		if (int_2 < int_4) {
			int_6 = int_2;
			int_2 = int_4;
			int_4 = int_6;
		}

		if (int_3 > this.x + this.width) {
			int_3 = this.x + this.width;
		}

		if (int_1 > this.x + this.width) {
			int_1 = this.x + this.width;
		}

		Tessellator tessellator_1 = Tessellator.getInstance();
		BufferBuilder bufferBuilder_1 = tessellator_1.getBufferBuilder();
		GlStateManager.color4f(0.0F, 0.0F, 255.0F, 255.0F);
		GlStateManager.disableTexture();
		GlStateManager.enableColorLogicOp();
		GlStateManager.logicOp(GlStateManager.LogicOp.OR_REVERSE);
		bufferBuilder_1.begin(7, VertexFormats.POSITION);
		bufferBuilder_1.vertex((double)int_1, (double)int_4, 0.0D).next();
		bufferBuilder_1.vertex((double)int_3, (double)int_4, 0.0D).next();
		bufferBuilder_1.vertex((double)int_3, (double)int_2, 0.0D).next();
		bufferBuilder_1.vertex((double)int_1, (double)int_2, 0.0D).next();
		tessellator_1.draw();
		GlStateManager.disableColorLogicOp();
		GlStateManager.enableTexture();
	}*/

	public WTextField setTextPredicate(Predicate<String> predicate_1) {
		this.textPredicate = predicate_1;
		return this;
	}
	
	public WTextField setChangedListener(Consumer<String> listener) {
		this.onChanged = listener;
		return this;
	}
	
	public WTextField setMaxLength(int max) {
		this.maxLength = max;
		if (this.text.length() > max) {
			this.text = this.text.substring(0, max);
			this.onChanged.accept(this.text);
		}
		return this;
	}
	
	public WTextField setEnabledColor(int col) {
		this.enabledColor = col;
		return this;
	}
	
	public WTextField setDisabledColor(int col) {
		this.uneditableColor = col;
		return this;
	}
	
	public WTextField setEditable(boolean editable) {
		this.editable = editable;
		return this;
	}
	
	public WTextField setSuggestion(@Nullable String suggestion) {
		this.suggestion = suggestion;
		return this;
	}
	
	public WTextField setSuggestion(@Nullable Text suggestion) {
		this.suggestion = suggestion.asString();
		return this;
	}
	
	@Environment(EnvType.CLIENT)
	public WTextField setBackgroundPainter(BackgroundPainter painter) {
		this.backgroundPainter = painter;
		return this;
	}
	/*
	public void method_1884(int int_1) {
		int int_2 = this.text.length();
		this.cursorMin = MathHelper.clamp(int_1, 0, int_2);
		if (this.textRenderer != null) {
			if (this.field_2103 > int_2) {
				this.field_2103 = int_2;
			}

			int int_3 = this.method_1859();
			String string_1 = this.textRenderer.trimToWidth(this.text.substring(this.field_2103), int_3);
			int int_4 = string_1.length() + this.field_2103;
			if (this.cursorMin == this.field_2103) {
				this.field_2103 -= this.textRenderer.trimToWidth(this.text, int_3, true).length();
			}

			if (this.cursorMin > int_4) {
				this.field_2103 += this.cursorMin - int_4;
			} else if (this.cursorMin <= this.field_2103) {
				this.field_2103 -= this.field_2103 - this.cursorMin;
			}

			this.field_2103 = MathHelper.clamp(this.field_2103, 0, int_2);
		}

	}*/

	
	
	/*
	@Environment(EnvType.CLIENT)
	public int getCharacterX(int int_1) {
		return int_1 > this.text.length() ? this.x : this.x + this.textRenderer.getStringWidth(this.text.substring(0, int_1));
	}*/
	
	public boolean canFocus() {
		return true;
	}
	
	@Override
	public void onFocusGained() {
	}
	
	@Override
	public void paintBackground(int x, int y) {
		
		/*
		if (isFocused()) {
			ScreenDrawing.rect(x-1, y-1, this.getWidth()+2, this.getHeight()+2, 0xFFFFFFFF);
		}
		
		if (backgroundPainter!=null) {
			backgroundPainter.paintBackground(x, y, this);
		} else {
			ScreenDrawing.drawBeveledPanel(x, y, this.getWidth(), this.getHeight());
		}
		
		ScreenDrawing.drawString(this.text, x+OFFSET_X_TEXT, y+OFFSET_Y_TEXT, 0xFFFFFFFF);
		//int ofs = MinecraftClient.getInstance().textRenderer.getStringWidth(this.text);
		ScreenDrawing.rect(x+OFFSET_X_TEXT+getCaretOffset(this.text, cursor), y+OFFSET_Y_TEXT-2, 1, OFFSET_Y_TEXT*2, 0xFFE0E0E0);*/
		
		renderButton(x, y);
	}
	
	@Override
	public void onClick(int x, int y, int button) {
		requestFocus();
		cursor = getCaretPos(this.text, x-OFFSET_X_TEXT);
	}
	
	@Override
	public void onCharTyped(char ch) {
		if (this.text.length()<this.maxLength) {
			//snap cursor into bounds if it went astray
			if (cursor<0) cursor=0;
			if (cursor>this.text.length()) cursor = this.text.length();
			
			String before = this.text.substring(0, cursor);
			String after = this.text.substring(cursor, this.text.length());
			this.text = before+ch+after;
			cursor++;
		}
	}
	
	@Override
	public void onKeyPressed(int ch, int key, int modifiers) {
		if (modifiers==0) {
			if (ch==GLFW.GLFW_KEY_DELETE || ch==GLFW.GLFW_KEY_BACKSPACE) {
			//if (key==22) {
				if (text.length()>0 && cursor>0) {
					String before = this.text.substring(0, cursor);
					String after = this.text.substring(cursor, this.text.length());
					
					before = before.substring(0,before.length()-1);
					text = before+after;
					cursor--;
				}
			} else if (ch==GLFW.GLFW_KEY_LEFT) {
				if (cursor>0) cursor--;
			} else if (ch==GLFW.GLFW_KEY_RIGHT) {
				if (cursor<text.length()) cursor++;
			} else {
				
				//System.out.println("Ch: "+ch+", Key: "+key+" GLFW: "+GLFW.GLFW_KEY_LEFT);
				
			}
		}
	}
	
	/**
	 * From an X offset past the left edge of a TextRenderer.draw, finds out what the closest caret
	 * position (division between letters) is.
	 * @param s
	 * @param x
	 * @return
	 */
	@Environment(EnvType.CLIENT)
	public static int getCaretPos(String s, int x) {
		if (x<=0) return 0;
		
		TextRenderer font = MinecraftClient.getInstance().textRenderer;
		int lastAdvance = 0;
		for(int i=0; i<s.length()-1; i++) {
			int advance = font.getStringWidth(s.substring(0,i+1));
			int charAdvance = advance-lastAdvance;
			if (x<advance + (charAdvance/2)) return i+1;
			
			lastAdvance = advance;
		}
		
		return s.length();
	}
	
	/**
	 * From a caret position, finds out what the x-offset to draw the caret is.
	 * @param s
	 * @param pos
	 * @return
	 */
	@Environment(EnvType.CLIENT)
	public static int getCaretOffset(String s, int pos) {
		if (pos==0) return 0;//-1;
		
		TextRenderer font = MinecraftClient.getInstance().textRenderer;
		return font.getStringWidth(s.substring(0, pos))+1;
	}
}