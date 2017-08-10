package com.feeljoy.library.annotation.view;

import android.text.TextUtils;
import android.view.View;
import android.widget.AbsListView;

import java.lang.reflect.Field;

/**    
 * @author cate
 * 2015-3-16 上午9:28:16   
 */
public class Ioc
{
	public static void initInjectedView(Object injectedSource,View sourceView){
		Field[] fields = injectedSource.getClass().getDeclaredFields();
		if(fields!=null && fields.length>0){
			for(Field field : fields){
				try {
					field.setAccessible(true);
					if(field.get(injectedSource)!= null )
						continue;
					ViewInject viewInject = field.getAnnotation(ViewInject.class);
					if(viewInject!=null){
						int viewId = viewInject.id();
					    field.set(injectedSource,sourceView.findViewById(viewId));
					    setListener(injectedSource,field,viewInject.click(), Method.Click);
						setListener(injectedSource,field,viewInject.longClick(), Method.LongClick);
						setListener(injectedSource,field,viewInject.itemClick(), Method.ItemClick);
						setListener(injectedSource,field,viewInject.itemLongClick(), Method.itemLongClick);
						Select select = viewInject.select();
						if(!TextUtils.isEmpty(select.selected())){
							setViewSelectListener(injectedSource,field,select.selected(),select.noSelected());
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private static void setViewSelectListener(Object injectedSource,Field field,String select,String noSelect)throws Exception{
		Object obj = field.get(injectedSource);
		if(obj instanceof View){
			((AbsListView)obj).setOnItemSelectedListener(new EventListener(injectedSource).select(select).noSelect(noSelect));
		}
	}
	
	private static void setListener(Object injectedSource,Field field,String methodName,Method method)throws Exception{
		if(methodName == null || methodName.trim().length() == 0)
			return;
		Object obj = field.get(injectedSource);
		switch (method) {
			case Click:
				if(obj instanceof View){
					((View)obj).setOnClickListener(new EventListener(injectedSource).click(methodName));
				}
				break;
			case ItemClick:
				if(obj instanceof AbsListView){
					((AbsListView)obj).setOnItemClickListener(new EventListener(injectedSource).itemClick(methodName));
				}
				break;
			case LongClick:
				if(obj instanceof View){
					((View)obj).setOnLongClickListener(new EventListener(injectedSource).longClick(methodName));
				}
				break;
			case itemLongClick:
				if(obj instanceof AbsListView){
					((AbsListView)obj).setOnItemLongClickListener(new EventListener(injectedSource).itemLongClick(methodName));
				}
				break;
			default:
				break;
		}
	}
	public enum Method{
		Click,LongClick,ItemClick,itemLongClick
	}
}
