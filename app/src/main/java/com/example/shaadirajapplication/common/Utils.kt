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

}
