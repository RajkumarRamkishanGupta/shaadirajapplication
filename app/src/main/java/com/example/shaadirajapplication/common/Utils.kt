package com.example.shaadirajapplication.common

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.view.View
import android.view.WindowManager
import android.widget.ProgressBar
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivity =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivity == null) {
            return false
        } else {
            val info = connectivity.allNetworkInfo
            for (networkInfo in info) {
                if (networkInfo.state == NetworkInfo.State.CONNECTED) {
                    return true
                }
            }
        }
        return false
    }

    fun hideProgressBar(activity: Activity?, progressBar: ProgressBar?) {
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        if (progressBar != null) {
            progressBar.visibility = View.GONE
        }
    }

    fun showProgressBar(activity: Activity?, progressBar: ProgressBar?) {
        activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
        if (progressBar != null) {
            progressBar.visibility = View.VISIBLE
        }
    }


    fun getFormatedDate(
        date: String
    ): String {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        val date: Date =
            dateFormat.parse(date) //You will get date object relative to server/client timezone wherever it is parsed

        val formatter: DateFormat =
            SimpleDateFormat("yyyy-MM-dd") //If you need time just put specific format for time like 'HH:mm:ss'

        return formatter.format(date)

    }
    // Root NavHost for Jetpack Compose @Composable fun MessengerAppNavHost(navController: NavHostController) { NavHost(navController = navController, startDestination = "splash") { composable("splash") { SplashScreen(navController) } composable("upload_photo") { UploadPhotoScreen(navController) } composable("login") { LoginScreen(navController) } composable("pin_input") { PinInputScreen(navController) } composable("contacts") { ContactListScreen(navController) } composable("messages") { MessageListScreen(navController) } composable("chat") { ChatScreen(navController) } composable("calls") { CallsScreen(navController) } composable("groups") { GroupsScreen(navController) } composable("media") { MediaScreen(navController) } } }

@Composable fun SplashScreen(navController: NavController) { Column( modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally ) { Text("MESSENGER", style = MaterialTheme.typography.h4) Spacer(modifier = Modifier.height(16.dp)) Button(onClick = { navController.navigate("upload_photo") }) { Text("Lorem Ipsum") } } }

@Composable fun UploadPhotoScreen(navController: NavController) { Column( modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally ) { Icon(Icons.Default.AccountCircle, contentDescription = null, modifier = Modifier.size(100.dp)) Spacer(modifier = Modifier.height(16.dp)) Text("Upload Your Photo") Spacer(modifier = Modifier.height(16.dp)) Button(onClick = { navController.navigate("login") }) { Text("Lorem Ipsum") } } }

@Composable fun LoginScreen(navController: NavController) { Column(modifier = Modifier.fillMaxSize().padding(16.dp)) { OutlinedTextField(value = "", onValueChange = {}, label = { Text("Email") }) OutlinedTextField(value = "", onValueChange = {}, label = { Text("Password") }) Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) { Checkbox(checked = false, onCheckedChange = {}) Text("I agree with T&C") } Button(onClick = { navController.navigate("pin_input") }, modifier = Modifier.align(Alignment.CenterHorizontally)) { Text("Lorem Ipsum") } } }

@Composable fun PinInputScreen(navController: NavController) { Column( modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally ) { Text("Enter PIN") Spacer(modifier = Modifier.height(8.dp)) Row(horizontalArrangement = Arrangement.Center) { repeat(3) { OutlinedTextField(value = "", onValueChange = {}, modifier = Modifier.width(40.dp)) } } Spacer(modifier = Modifier.height(16.dp)) Button(onClick = { navController.navigate("contacts") }) { Text("Lorem Ipsum") } } }

@Composable fun ContactListScreen(navController: NavController) { LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) { items(5) { index -> Row( modifier = Modifier.fillMaxWidth().clickable { navController.navigate("chat") }.padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically ) { Icon(Icons.Default.Person, contentDescription = null, modifier = Modifier.size(40.dp)) Spacer(modifier = Modifier.width(8.dp)) Text("Contact ${index + 1}") } } } }

@Composable fun MessageListScreen(navController: NavController) { LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) { items(5) { index -> ListItem(headlineText = { Text("Message from Contact ${index + 1}") }, supportingText = { Text("Lorem ipsum dolor sit amet") }) } } }

@Composable fun ChatScreen(navController: NavController) { Column(modifier = Modifier.fillMaxSize().padding(16.dp)) { LazyColumn(modifier = Modifier.weight(1f)) { items(10) { index -> Text("Message ${index + 1}", modifier = Modifier.padding(vertical = 4.dp)) } } Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) { OutlinedTextField(value = "", onValueChange = {}, modifier = Modifier.weight(1f)) IconButton(onClick = {}) { Icon(Icons.Default.Send, contentDescription = null) } } } }

@Composable fun CallsScreen(navController: NavController) { LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) { items(5) { index -> ListItem( leadingContent = { Icon(Icons.Default.Phone, contentDescription = null) }, headlineText = { Text("Call ${index + 1}") }, supportingText = { Text("Date and time") } ) } } }

@Composable fun GroupsScreen(navController: NavController) { LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) { items(3) { index -> ListItem(headlineText = { Text("Group ${index + 1}") }, supportingText = { Text("Group description") }) } } }

@Composable fun MediaScreen(navController: NavController) { LazyVerticalGrid(columns = GridCells.Fixed(3), modifier = Modifier.fillMaxSize().padding(16.dp)) { items(9) { index -> Box(modifier = Modifier .padding(4.dp) .aspectRatio(1f) .background(Color.Gray)) { Text("Image ${index + 1}", modifier = Modifier.align(Alignment.Center), color = Color.White) } } } }

// In MainActivity.kt class MainActivity : ComponentActivity() { override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState) setContent { val navController = rememberNavController() MessengerAppNavHost(navController = navController) } } }

@Composable
fun MessengerWelcomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEAF2FB)), // Light background
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "MESSENGER",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Lorem ipsum dolor sit amet,\nconsectetur adipiscing elit, sed",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Emoji with message bubble
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Message Icon",
                    tint = Color.White,
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color(0xFF5A8DEE), CircleShape)
                        .padding(20.dp)
                )
                Text(
                    text = "\uD83D\uDE03", // Smiley Emoji
                    fontSize = 40.sp,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .offset(x = 30.dp, y = (-30).dp)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = { /* TODO: Handle button click */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5A8DEE))
            ) {
                Text(text = "LOREM IPSUM", color = Color.White)
            }
        }
    }
}

@Composable
fun RegistrationScreen() {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isAgreed by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        // Upload Icon Placeholder
        Icon(
            imageVector = Icons.Default.CameraAlt,
            contentDescription = "Upload Icon",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color(0xFFE0F0FF))
                .padding(16.dp)
        )
        Text(text = "Upload Your Photo", modifier = Modifier.padding(8.dp))

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Lorem ipsum") })
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Dolor sit") })
        OutlinedTextField(value = username, onValueChange = { username = it }, label = { Text("Amet") })
        OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Lorem ipsum") })

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = isAgreed, onCheckedChange = { isAgreed = it })
            Text(text = "I agree with T&C")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { /* Handle form submission */ }, modifier = Modifier.fillMaxWidth()) {
            Text("LOREM IPSUM")
        }
    }
}

@Composable
fun ProfileMenuScreen(onMenuClick: (String) -> Unit) {
    val menuItems = listOf(
        "Lorem ipsum" to Icons.Default.Home,
        "Dolor sit" to Icons.Default.Notifications,
        "Amet lorem" to Icons.Default.Lock,
        "Ipsum dolor" to Icons.Default.Email,
        "Sit amet" to Icons.Default.Settings,
        "Lorem ipsum" to Icons.Default.ExitToApp
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile",
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE0F0FF))
                    .padding(8.dp)
            )
            Column(modifier = Modifier.padding(start = 8.dp)) {
                Text("Lorem ipsum Name", fontWeight = FontWeight.Bold)
                Text("Dolor sit", color = Color.Gray)
            }
        }

        Divider()

        menuItems.forEach { (label, icon) ->
            ListItem(
                headlineContent = { Text(label) },
                leadingContent = {
                    Icon(imageVector = icon, contentDescription = label)
                },
                modifier = Modifier.clickable { onMenuClick(label) }
            )
        }
    }
}

Creating a chat app using Jetpack Compose, Hilt, MVVM, and Clean Architecture in Kotlin involves several layers and responsibilities. Here's an overview structure followed by a basic project skeleton you can build upon:

1. Project Structure (Clean Architecture + MVVM)
com.example.chatapp
|
├── data
│   ├── model
│   ├── repository
│   ├── source (local/remote)
│   └── di
|
├── domain
│   ├── model
│   ├── repository
│   └── usecase
|
├── presentation
│   ├── chat
│   │   ├── ChatViewModel.kt
│   │   ├── ChatScreen.kt
│   └── navigation
|
├── MainActivity.kt
└── App.kt
2. Key Libraries
Jetpack Compose UI
Hilt (for Dependency Injection)
Kotlin Coroutines + Flow
Room (optional for local chat caching)
Retrofit / WebSockets (for remote messages)
Firebase or Socket.io (for real-time messaging, optional)
3. Sample Components
ChatMessage.kt (domain model)
data class ChatMessage(
    val id: String,
    val sender: String,
    val message: String,
    val timestamp: Long
)
ChatRepository.kt (domain layer)
interface ChatRepository {
    fun getMessages(): Flow<List<ChatMessage>>
    suspend fun sendMessage(message: ChatMessage)
}
ChatUseCase.kt
class ChatUseCase(private val repository: ChatRepository) {
    fun getMessages() = repository.getMessages()
    suspend fun sendMessage(message: ChatMessage) = repository.sendMessage(message)
}
ChatViewModel.kt
@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatUseCase: ChatUseCase
) : ViewModel() {

    val messages = chatUseCase.getMessages().stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        emptyList()
    )

    fun sendMessage(content: String) {
        val msg = ChatMessage(
            id = UUID.randomUUID().toString(),
            sender = "You",
            message = content,
            timestamp = System.currentTimeMillis()
        )
        viewModelScope.launch {
            chatUseCase.sendMessage(msg)
        }
    }
}
ChatScreen.kt
@Composable
fun ChatScreen(viewModel: ChatViewModel = hiltViewModel()) {
    val messages by viewModel.messages.collectAsState()

    Column {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(messages) { msg ->
                Text("${msg.sender}: ${msg.message}")
            }
        }
        var input by remember { mutableStateOf("") }
        Row {
            TextField(value = input, onValueChange = { input = it }, modifier = Modifier.weight(1f))
            Button(onClick = {
                viewModel.sendMessage(input)
                input = ""
            }) {
                Text("Send")
            }
        }
    }
}
Would you like a GitHub-ready starter template with WebSocket or Firebase integration?



Arundhati Gupta <arundhatig9426@gmail.com>
May 1, 2025, 9:40 PM (14 hours ago)
to me

Yes, you must initialize Hilt at the application level to enable dependency injection throughout your app.

Steps to Initialize Hilt in Application Class
1. Create the Application Class
@HiltAndroidApp
class ChatApp : Application()
@HiltAndroidApp triggers Hilt’s code generation and sets up the application-level dependency container.
2. Register in AndroidManifest.xml
<application
    android:name=".ChatApp"
    ... >
    ...
</application>
Why It's Necessary
Without this setup:

Hilt cannot generate the necessary components.
You’ll get runtime errors like java.lang.IllegalStateException: Hilt does not have a generated component for this class.



    Thanks for the clarification — since you're not using a database, Hilt still needs a way to provide your ChatUseCase, but without a ChatRepositoryImpl backed by Room.

Here’s how to fix your issue without using Room or a database:

✅ Step-by-Step: Fix Hilt Missing Binding Without Database
1. Provide a Fake/In-Memory Repository
Create a simple in-memory implementation:

class ChatRepositoryImpl @Inject constructor() : ChatRepository {

    private val messages = MutableStateFlow<List<ChatMessage>>(emptyList())

    override fun getMessages(): Flow<List<ChatMessage>> = messages

    override suspend fun sendMessage(message: ChatMessage) {
        messages.value = messages.value + message
    }
}
2. Make ChatUseCase Injectable
Update it like this:

class ChatUseCase @Inject constructor(
    private val repository: ChatRepository
) {
    fun getMessages() = repository.getMessages()
    suspend fun sendMessage(message: ChatMessage) = repository.sendMessage(message)
}
3. Provide the Repository to Hilt
Create a module:

@Module
@InstallIn(SingletonComponent::class)
object ChatModule {

    @Provides
    fun provideChatRepository(): ChatRepository = ChatRepositoryImpl()
}
With this setup, Hilt can provide ChatUseCase through constructor injection.

Now you can inject ChatUseCase into your ViewModel:

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatUseCase: ChatUseCase
) : ViewModel() {
    // ViewModel logic here
}
Let me know if you want a complete minimal project structure or if you'd like to enable persistent chat using preferences or files instead of Room.

Great! Let’s start by building the UI layer for the chat screen using Jetpack Compose. This will include:

Top App Bar
LazyColumn for messages
Message Bubbles (for sender and receiver)
Bottom Input Bar
ChatScreen.kt
@Composable
fun ChatScreen(
    messages: List<ChatMessage>,
    onMessageSend: (String) -> Unit
) {
    var currentMessage by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        TopBar()

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp),
            reverseLayout = true
        ) {
            items(messages.reversed()) { message ->
                MessageBubble(message)
            }
        }

        BottomBar(
            message = currentMessage,
            onMessageChange = { currentMessage = it },
            onSend = {
                if (currentMessage.isNotBlank()) {
                    onMessageSend(currentMessage)
                    currentMessage = ""
                }
            }
        )
    }
}
TopBar.kt
@Composable
fun TopBar() {
    TopAppBar(
        title = { Text("Chat", fontWeight = FontWeight.Bold) },
        actions = {
            IconButton(onClick = {}) {
                Icon(Icons.Default.Person, contentDescription = "Profile")
            }
            IconButton(onClick = {}) {
                Icon(Icons.Default.Search, contentDescription = "Search")
            }
        },
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 4.dp
    )
}
MessageBubble.kt
@Composable
fun MessageBubble(message: ChatMessage) {
    val isMe = message.isMe
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = if (isMe) Arrangement.End else Arrangement.Start
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = if (isMe) Color(0xFFE0F7FA) else Color(0xFFF1F1F1),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(10.dp)
                .widthIn(max = 250.dp)
        ) {
            Text(text = message.text, style = MaterialTheme.typography.body2)
        }
    }
}
BottomBar.kt
@Composable
fun BottomBar(
    message: String,
    onMessageChange: (String) -> Unit,
    onSend: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = message,
            onValueChange = onMessageChange,
            modifier = Modifier.weight(1f),
            placeholder = { Text("Type a message") },
            maxLines = 4
        )
        IconButton(onClick = onSend) {
            Icon(Icons.Default.Send, contentDescription = "Send")
        }
    }
}
ChatMessage.kt (Model in UI layer)
data class ChatMessage(
    val text: String,
    val isMe: Boolean
)


Sure! Here's a complete step-by-step setup for a local Room database in your Jetpack Compose + Hilt + MVVM chat app, with fields:

id: Int
sender: String
message: String
timestamp: Long
1. Add dependencies (in build.gradle)
// Room
implementation "androidx.room:room-runtime:2.6.1"
kapt "androidx.room:room-compiler:2.6.1"
implementation "androidx.room:room-ktx:2.6.1"

// Hilt
implementation "com.google.dagger:hilt-android:2.50"
kapt "com.google.dagger:hilt-android-compiler:2.50"
implementation "androidx.hilt:hilt-navigation-compose:1.2.0"
2. Enable kapt (top-level build.gradle)
apply plugin: 'kotlin-kapt'
3. Create the ChatMessage entity
@Entity(tableName = "chat_messages")
data class ChatMessage(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val sender: String,
    val message: String,
    val timestamp: Long
)
4. DAO (Data Access Object)
@Dao
interface ChatDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: ChatMessage)

    @Query("SELECT * FROM chat_messages ORDER BY timestamp ASC")
    fun getAllMessages(): Flow<List<ChatMessage>>
}
5. Database
@Database(entities = [ChatMessage::class], version = 1)
abstract class ChatDatabase : RoomDatabase() {
    abstract fun chatDao(): ChatDao
}
6. Hilt Module
@Module
@InstallIn(SingletonComponent::class)
object ChatDatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): ChatDatabase {
        return Room.databaseBuilder(
            context,
            ChatDatabase::class.java,
            "chat_db"
        ).build()
    }

    @Provides
    fun provideChatDao(db: ChatDatabase): ChatDao = db.chatDao()
}
7. Repository
interface ChatRepository {
    suspend fun insertMessage(message: ChatMessage)
    fun getMessages(): Flow<List<ChatMessage>>
}

class ChatRepositoryImpl @Inject constructor(
    private val dao: ChatDao
) : ChatRepository {
    override suspend fun insertMessage(message: ChatMessage) = dao.insertMessage(message)
    override fun getMessages(): Flow<List<ChatMessage>> = dao.getAllMessages()
}
8. UseCase (optional)
class ChatUseCase @Inject constructor(private val repository: ChatRepository) {
    suspend fun sendMessage(message: ChatMessage) = repository.insertMessage(message)
    fun getMessages(): Flow<List<ChatMessage>> = repository.getMessages()
}
9. ViewModel
@HiltViewModel
class ChatViewModel @Inject constructor(
    private val useCase: ChatUseCase
) : ViewModel() {

    val messages = useCase.getMessages().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    fun sendMessage(text: String, sender: String) {
        viewModelScope.launch {
            useCase.sendMessage(
                ChatMessage(sender = sender, message = text, timestamp = System.currentTimeMillis())
            )
        }
    }
}
10. Application Class
@HiltAndroidApp
class MyApp : Application()
Declare this class in AndroidManifest.xml:

<application
    android:name=".MyApp"
    ...>
Done!
This sets up a full Room database with Hilt and MVVM for a local chat message history.

Would you like the Compose UI implementation example next?

Show quoted text


Sure! Here's a complete step-by-step setup for a local Room database in your Jetpack Compose + Hilt + MVVM chat app, with fields:

id: Int
sender: String
message: String
timestamp: Long
1. Add dependencies (in build.gradle)
// Room
implementation "androidx.room:room-runtime:2.6.1"
kapt "androidx.room:room-compiler:2.6.1"
implementation "androidx.room:room-ktx:2.6.1"

// Hilt
implementation "com.google.dagger:hilt-android:2.50"
kapt "com.google.dagger:hilt-android-compiler:2.50"
implementation "androidx.hilt:hilt-navigation-compose:1.2.0"
2. Enable kapt (top-level build.gradle)
apply plugin: 'kotlin-kapt'
3. Create the ChatMessage entity
@Entity(tableName = "chat_messages")
data class ChatMessage(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val sender: String,
    val message: String,
    val timestamp: Long
)
4. DAO (Data Access Object)
@Dao
interface ChatDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: ChatMessage)

    @Query("SELECT * FROM chat_messages ORDER BY timestamp ASC")
    fun getAllMessages(): Flow<List<ChatMessage>>
}
5. Database
@Database(entities = [ChatMessage::class], version = 1)
abstract class ChatDatabase : RoomDatabase() {
    abstract fun chatDao(): ChatDao
}
6. Hilt Module
@Module
@InstallIn(SingletonComponent::class)
object ChatDatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): ChatDatabase {
        return Room.databaseBuilder(
            context,
            ChatDatabase::class.java,
            "chat_db"
        ).build()
    }

    @Provides
    fun provideChatDao(db: ChatDatabase): ChatDao = db.chatDao()
}
7. Repository
interface ChatRepository {
    suspend fun insertMessage(message: ChatMessage)
    fun getMessages(): Flow<List<ChatMessage>>
}

class ChatRepositoryImpl @Inject constructor(
    private val dao: ChatDao
) : ChatRepository {
    override suspend fun insertMessage(message: ChatMessage) = dao.insertMessage(message)
    override fun getMessages(): Flow<List<ChatMessage>> = dao.getAllMessages()
}
8. UseCase (optional)
class ChatUseCase @Inject constructor(private val repository: ChatRepository) {
    suspend fun sendMessage(message: ChatMessage) = repository.insertMessage(message)
    fun getMessages(): Flow<List<ChatMessage>> = repository.getMessages()
}
9. ViewModel
@HiltViewModel
class ChatViewModel @Inject constructor(
    private val useCase: ChatUseCase
) : ViewModel() {

    val messages = useCase.getMessages().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    fun sendMessage(text: String, sender: String) {
        viewModelScope.launch {
            useCase.sendMessage(
                ChatMessage(sender = sender, message = text, timestamp = System.currentTimeMillis())
            )
        }
    }
}
10. Application Class
@HiltAndroidApp
class MyApp : Application()
Declare this class in AndroidManifest.xml:

<application
    android:name=".MyApp"
    ...>
Done!
This sets up a full Room database with Hilt and MVVM for a local chat message history.

Would you like the Compose UI implementation example next?

Show quoted text

}
