
# Zapp Studio template.


In order to set an standard in Zapp-Studio!  I maked this colaborative project that will be improve to minimize the boilerplate code in projects.

#What is the target of project?
The goal of this project is to give our developers a initial project with all tools and updated standard code. All ways we code must be integrated in this project like tipography convections and all code.

## TODO list to start a project!!
- Use this project as a template with the button use as a template int github
- Change the bundle to the new app
- Change in Module.kt key of remote config node
- Change getVersionName in BuildConstants.
- Change endpoints base path in app/build.gradle.kts
- Add the keystore release to the buildsystem/keystore/release.jks
- Add credentials to app/build.gradle.kts for release
- Prepare github actions
- Change json files for Firebase use in (app/src/production app/src/develop) enable api crashlytics
- Create a tag into remote config Firebase with the following structure
{    
"url_store_ios": "",
"version_ios": "1.0.0",
"url_store_android": "",
"version_android": "1.0.0"
}

- Add the following copyright notice:

       Copyright (c) 2022.    
       All rights reserved to Zapp Studio S.L    
       Av. de Zarandona, 29, 1 planta, 30007 Murcia  

  For adding this you have to go to File/Preferences(Android Studio/Preferences in MacOs) / Editor/Copyright/Copyright Profiles

### Enjoy it!!

# Code style
In order to use the same code-styles, please make your android studio get the default format and change the hard wrap at 250

### Names styles:
- **ViewModels:** Compound name fragment/activity + viewModel               
  Example: UserViewModel, ProductViewModel

- **Models:** Compound name model + Model               
  Example: UserModel

- **Enum Type Model:** Compound name model + Specific type identifier (if needed) + TypeModel            
  Example: UserTypeModel, UserSubscriptionTypeModel

- **Entities:** Compound name entity + Entity              
  Example: UserEntity

- **Request Model:** Compound name request + Request               
  Example: RegisterRequest

- **Mapper:** Compound name model + Mapper              
  Example: UserMapper

- **Interactor:** Compound name model + Mapper              
  Example: UserMapper

- **Layouts:** Fragments -> fragment_user            
  Activities -> activity_user            
  Rows      -> item_user, item_product              
  Lists headers -> header_users             
  Pop ups -> dialog_user_profile

- **Drawables and colors:**
- Icons -> *icn_[icon name]* -> Ex: icn_gallery
  - Gradients -> *gradient_[first color] _ [second color] _ [shape]* -> Ex: gradient_color3_transparent_rounded8
  - Color selector -> *selector_ [off color] _ [on color]* -> Ex: selector_color0_color0alpha54
  - Shapes -> *shape _ [rounded[size] | circle] _  [color] _ [stroke_[color stroke]_[size]]* ->          
    Ex: shape_rounded16_color0_stroke_color0_2dp

- **Request body**
- Request body will be named with Request(Feature name), it will be placed in the same api class just below calls, and all together, in the case request call will be reused, it will be placed in a requests folder at the same level of api.
- Request can be used in view models only for request which entitys doenst match with the properly request it will be named as Reques(Feature name)Model and it will be parse to Request(Feature name) before will be send.


# Styles convections
All styles must be defined with the same scheme

AppTheme.component.attributes Attributes whose identify the particularity of component must be defined with attribute-value

AppTheme.component.hint-color0.background-color1 Examples:

**Buttons**

AppTheme.ButtonAppTheme.Button.textColor-color0 **Text** AppTheme.H1 AppTheme.H1.textColor-color1    AppTheme.H1.underlined

Headline 1 -> H1    Headline 2 -> H2               
Headline 3 -> H3                  
Headline 4 -> H4                  
....

Subtitle 1 -> S1    Subtitle 2 -> S2              
....

Body 1 -> B1 Body 2 -> B2 ....           
**Backgrounds** AppTheme.BG           
AppTheme.BG1          
AppTheme.BG.backgroundColor-color0              
**CheckBox** AppTheme.Checkbox

**Inputs** There are two ways to make an input.
1) Text input layout that It means two styles by input.
2) EditText, which it mean one style per input.

AppTheme.TextInputLayout    AppTheme.EditText            
Be free to use what you need or both of them

Popup & dialogs you  can found  in  the  template  the  necessary  style  be  free  to  customize  for  your  needs.

 <style name="AppTheme.Dialog.Button.Positive" parent="AppTheme.Button">        </style>            

 <style name="AppTheme.Dialog.Button.Negative" parent="AppTheme.Dialog.Button.Positive"> </style>     <style name="AppTheme.Dialog.LinearLayoutButtons">        </style>              

 <style name="AppTheme.Dialog.Title" parent="AppTheme.Form.Title">        </style>                  

 <style name="AppTheme.Dialog.Text" parent="AppTheme.Form.Label"> </style>                   
 <style name="AppTheme.Dialog.Container"></style>   
 <style name="AppTheme.Dialog.CloseButton">        </style>            

...


## Herramientas de compilación para Android.

El objetivo, es configurar un entorno de compilación claro y seguro, que pueda ser ejecutado, tanto en el servidor de integración continua, como en local con un entorno seguro, para esto nos vamos a apoyar en el siguiente stack de tecnologías.

[Fastlane.](https://fastlane.tools/)    
[Docker](https://www.docker.com/)  
[Github actions](https://github.com/features/actions)  
[Make](https://es.wikipedia.org/wiki/Make)

Fastlane, son un conjunto de herramientas, para integración continua, muy enfocado a Android.

Docker, este se usara, para dar facilidad a la hora del deployment, a causa de Android Studio, es común no tener bien configurado el entorno de desarrollo local, pues Android Studio lo tiene integrado todo. Esto causa que normalmente no se tenga configurado JAVA_HOME / ANDROID_SDK_ROOT / gradle y diferentes herramientas que Android Studio integran dentro de este.

**Github actions.** Las acciones de Github lanzaran una imagen de Docker con los comandos de Fastlane.    
**develop** -> Se lanzara manualmente cuando el desarrollador quiera y en este plantilla lanzara develop.  
**production** -> Siempre se lanzara cuando se haga un push de código a master y lanzara el lane de production y productionBundle.

Lanes, un lane o camino es unos determinados comandos agrupados e identificados como un lane.  
Por defecto tenemos 3 lanes diferentes.  
**develop** Genera una developRelease (APK) y la sube a Appcenter  
**production** Genera una productionRelease (APK) y la sube a Appcenter  
**productionBundle** Genera una productionRelease (AAB) y la sube a Appcenter.

#### Configuración
Dentro de la carpeta Fastlane, podemos encontrar un fichero config.json con la siguiente estructura. En la misma carpeta podemos encontrar app file  donde debemos meter el app bundle.


fastlane/AppFile json_key_file("") # Path to the json secret file - Follow https://docs.fastlane.tools/actions/supply/#setup to get one    package_name("com.template.app") # e.g. com.krausefx.app

fastlane/Config.json {      "appcenter_api_key" : "API_KEY_APPCENTER",    
"app_name_appcenter" : "NOMBRE APLICACION EN APPCENTER",  
// El nombre que sale en la url cuando entras dentro de un proyecto en Appcenter.      "owner_name_user" : "USER_APPCENTER"  
}Para el caso de la plantilla solo hay que cambiar el app_name_appcenter y package_name.
# Zapp Studio template.


In order to set an standard in Zapp-Studio!  I maked this colaborative project that will be improve to minimize the boilerplate code in projects.

#What is the target of project?
The goal of this project is to give our developers a initial project with all tools and updated standard code. All ways we code must be integrated in this project like tipography convections and all code.

### Enjoy it!!

# Code style
In order to use the same code-styles, please make your android studio get the default format and change the hard wrap at 250 ,

Change File -> Settings  -> Editor ->Code Style -> Line separator to unix and macOS

### Names styles:
- **ViewModels:** Compound name fragment/activity + viewModel                 
  Example: UserViewModel, ProductViewModel

- **Models:** Compound name model + Model                 
  Example: UserModel

- **Enum Type Model:** Compound name model + Specific type identifier (if needed) + TypeModel              
  Example: UserTypeModel, UserSubscriptionTypeModel

- **Entities:** Compound name entity + Entity                
  Example: UserEntity

- **Request Model:** Compound name request + Request                 
  Example: RegisterRequest

- **Mapper:** Compound name model + Mapper                
  Example: UserMapper

- **Interactor:** Compound name model + Mapper                
  Example: UserMapper

- **Layouts:** Fragments -> fragment_user              
  Activities -> activity_user              
  Rows      -> item_user, item_product                
  Lists headers -> header_users               
  Pop ups -> dialog_user_profile

- **Drawables and colors:**
- Icons -> *icn_[icon name]* -> Ex: icn_gallery
  - Gradients -> *gradient_[first color] _ [second color] _ [shape]* -> Ex: gradient_color3_transparent_rounded8
  - Color selector -> *selector_ [off color] _ [on color]* -> Ex: selector_color0_color0alpha54
  - Shapes -> *shape _ [rounded[size] | circle] _  [color] _ [stroke_[color stroke]_[size]]* ->            
    Ex: shape_rounded16_color0_stroke_color0_2dp

- **Request body**
- Request body will be named with Request(Feature name), it will be placed in the same api class just below calls, and all together, in the case request call will be reused, it will be placed in a requests folder at the same level of api.
- Request can be used in view models only for request which entitys doenst match with the properly request it will be named as Reques(Feature name)Model and it will be parse to Request(Feature name) before will be send.


# Styles convections
All styles must be defined with the same scheme

AppTheme.component.attributes Attributes whose identify the particularity of component must be defined with attribute-value

AppTheme.component.hint-color0.background-color1 Examples:

**Buttons**

AppTheme.ButtonAppTheme.Button.textColor-color0 **Text** AppTheme.H1 AppTheme.H1.textColor-color1    AppTheme.H1.underlined

Headline 1 -> H1    Headline 2 -> H2                 
Headline 3 -> H3                    
Headline 4 -> H4                    
....

Subtitle 1 -> S1    Subtitle 2 -> S2                
....

Body 1 -> B1 Body 2 -> B2 ....             
**Backgrounds** AppTheme.BG             
AppTheme.BG1            
AppTheme.BG.backgroundColor-color0                
**CheckBox** AppTheme.Checkbox

**Inputs** There are two ways to make an input.
1) Text input layout that It means two styles by input.
2) EditText, which it mean one style per input.

AppTheme.TextInputLayout    AppTheme.EditText              
Be free to use what you need or both of them

Popup & dialogs you  can found  in  the  template  the  necessary  style  be  free  to  customize  for  your  needs.

 <style name="AppTheme.Dialog.Button.Positive" parent="AppTheme.Button">        </style>              

 <style name="AppTheme.Dialog.Button.Negative" parent="AppTheme.Dialog.Button.Positive"> </style>     <style name="AppTheme.Dialog.LinearLayoutButtons">        </style>                

 <style name="AppTheme.Dialog.Title" parent="AppTheme.Form.Title">        </style>                    

 <style name="AppTheme.Dialog.Text" parent="AppTheme.Form.Label"> </style>                     
 <style name="AppTheme.Dialog.Container"></style>     
 <style name="AppTheme.Dialog.CloseButton">        </style>              

...


## Herramientas de compilación para Android.

El objetivo, es configurar un entorno de compilación claro y seguro, que pueda ser ejecutado, tanto en el servidor de integración continua, como en local con un entorno seguro, para esto nos vamos a apoyar en el siguiente stack de tecnologías.

[Fastlane.](https://fastlane.tools/)        
[Docker](https://www.docker.com/)   [Imagen docker](https://hub.docker.com/r/mingc/android-build-box/)    
[Github actions](https://github.com/features/actions)      
[Make](https://es.wikipedia.org/wiki/Make)

Fastlane, son un conjunto de herramientas, para integración continua, muy enfocado a Android.

Docker, este se usara, para dar facilidad a la hora del deploy, a causa de Android Studio, es común no tener bien configurado el entorno de desarrollo local, pues Android Studio lo tiene integrado todo. Esto causa que normalmente no se tenga configurado JAVA_HOME / ANDROID_SDK_ROOT / gradle y diferentes herramientas que Android Studio integran dentro de este.

**Github actions.** Las acciones de Github lanzaran una imagen de Docker con los comandos de Fastlane.        
**develop** -> Se lanzara manualmente cuando el desarrollador quiera y en este plantilla lanzara develop.      
**production** -> Siempre se lanzara cuando se haga un push de código a master y lanzara el lane de production y productionBundle.

Lanes, un lane o camino es unos determinados comandos agrupados e identificados como un lane.      
Por defecto tenemos 3 lanes diferentes.

**fastlane buildAab flavor: (flavor_name)** Genera aab, sube este a Google play si este esta configurado, notifica por teams si este esta configurado.  
**fastlane generic flavor:(flavor_name)** Genera apk, sube a AppCenter, notifica por teams si este esta configurado.


#### Configuración
Dentro de la carpeta Fastlane, podemos encontrar un fichero **config_example.json** con la siguiente estructura. Debemos rellenar este con la configuracion del proyecto e incluirlo dentro de buildsystem.



{      "flavors": [    
{    
"flavor_name": "develop",    
"sign_conf": "release",    
"appcenter_api_key" : "9aecefa71de38e9fb018a372e155601ca98f437b",    
"appcenter_user" : "info-gdiv",    
"appcenter_app_name": "android-template",    
"appcenter_group": "develop",    
"appcenter_url_release": "https://install.appcenter.ms/users/info-gdiv/apps/android-template/distribution_groups/develop",    
"teams_web_hook": "https://zappstudio.webhook.office.com/webhookb2/337b9ab2-4ef4-4222-aca2-7083d0d252f0@c9205c9c-40ec-4671-89a6-8d9c3c03d826/IncomingWebhook/494ad3ccb8394f45b697419efe59bf77/73c0745e-68bd-4791-8bcb-61621dcca4cc",    
"json_key_file": "./fastlane/google_play_credentials.json",    
"package_name": "com.notrick.app"    
},    
{    
"flavor_name": "production",    
"sign_conf": "release",    
"appcenter_api_key" : "9aecefa71de38e9fb018a372e155601ca98f437b",    
"appcenter_user" : "info-gdiv",    
"appcenter_app_name": "android-template",    
"appcenter_group": "production",    
"appcenter_url_release": "https://install.appcenter.ms/users/info-gdiv/apps/android-template/distribution_groups/production",    
"teams_web_hook":    "https://zappstudio.webhook.office.com/webhookb2/337b9ab2-4ef4-4222-aca2-7083d0d252f0@c9205c9c-40ec-4671-89a6-8d9c3c03d826/IncomingWebhook/494ad3ccb8394f45b697419efe59bf77/73c0745e-68bd-4791-8bcb-61621dcca4cc",    
"json_key_file": "./fastlane/google_play_credentials.json",    
"package_name": "com.notrick.app"    
}    
]    
}


#### Uso local
Para evitar que el mismo entorno, contamine la ejecución de los comandos de Fastlane, estos siempre los ejecutaremos en una maquina virtual de Docker, para esto es obligatorio, instalar Docker y Make, los comandos se ejecutaran de la siguiente forma.  Esto evitara problemas al proporcionar un entorno de ejecución seguro, donde están instalados todo el software necesario.

**make develop** Este comando ejecuta el lane  
**fastlane generic flavor:develop**

**make production** Este comando ejecuta el lane de production dentro de Docker.      
**fastlane generic flavor:production**

**make all**  
**fastlane generic flavor:all**

**make build-aab** Este comando ejecuta el lane de.  
**fastlane buildAab flavor:production**

Extensiones futuras de nuevos comandos.

- [ ] Hacer seguimiento del bug de appcenter sobre subir aabs. https://github.com/microsoft/fastlane-plugin-appcenter/issues/298
- [x] Crear una tarea que envie la apk a Google Play beta
- [ ] Crear una tarea que se pueda lanzar desde Google actions que 1) Actualice el localise. 2) Suba el numero de versión y el versión name. 3) Compile y suba a Appcenter 4) Compile y suba a Google Play beta.

Con esto conseguimos separar nuestro entorno y problemas, JAVA_HOME, SDK_HOME y otras muchas variables del software, Ruby para Fastlane, etc, dejando todo esto a la imagen de Docker que contiene todo ese software.  
https://hub.docker.com/r/mingc/android-build-box/
