import SwiftUI
import shared

func greet() -> String {
    return Greeting().greeting()
}

func staticList() -> [Recipe] {
    DataSource().getStaticList()
}

struct ContentView: View {
    var body: some View {
        //Text(greet())
        Text(staticList()[0].title)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
