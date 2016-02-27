![Alt](https://raw.github.com/DayS/EnhancedEditText/master/header.png)

**EnhancedEditText** allows you to simply add [Iconify](https://github.com/JoanZapata/android-iconify) or text on each end of an EditText. It's awesome to get rid of TextView aligned with the component to say what's the purpose of the field, or to show value unit.

# Get started

You just have to add the namespace to be able to use custom attributes, and configure it like this :

```xml
<RelativeLayout	xmlns:android="http://schemas.android.com/apk/res/android"
              	xmlns:custom="http://schemas.android.com/apk/res/fr.dvilleneuve.android" />

	<EnhancedEditText
		android:layout_height="wrap_content"
		android:layout_width="match_parent"
		custom:prefixIcon="icon_dashboard"
		custom:suffixText="km/h"/>

</RelativeLayout>
```

# Get it

Just add the following dependency to your project

```
	repositories {
        // ...
        maven { url "https://jitpack.io" }
    }
```

```
	dependencies {
	        compile 'com.github.DayS:EnhancedEditText:-SNAPSHOT'
	}
```

# License

```
Copyright 2013 Damien Villeneuve

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

It uses Iconify by Joan Zapata, licensed under Apache License version 2, which is compatible
with this library's license.
```
