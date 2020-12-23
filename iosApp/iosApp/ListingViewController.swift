//
//  ListingViewController.swift
//  iosApp
//
//  Created by Soumyajit Das on 14/12/20.
//  Copyright © 2020 orgName. All rights reserved.
//

import UIKit
import shared

class ListingViewController: UIViewController {

    @IBOutlet weak var tableView: UITableView!
    var recipeList: [BrewaryResponseItem] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        fetchRecipe { [weak self] (recipeList) in
            if(recipeList is ResultHandlerSuccess) {
                let list = recipeList as! ResultHandlerSuccess
                self?.recipeList = list.data as! [BrewaryResponseItem]
                DispatchQueue.main.async {
                    self?.tableView.reloadData()
                }
            }
        }
    }
    
    func staticList() -> [Recipe] {
        DataSource().getStaticList()
    }
    
    func fetchRecipe(completionHandler: @escaping (ResultHandler<NSArray>) -> Void) {
        DataSource().fetchListFromNetwork(completionHandler: { (data, error) in
            if let error = error {
              print("Error with fetching films: \(error)")
              return
            }
            if let recipe = data {
                print("Data recieved from Shared module: \(String(describing: data))")
                completionHandler(recipe)
            }
        })
    }
}

extension ListingViewController: UITableViewDataSource, UITableViewDelegate {
   
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return recipeList.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let item = recipeList[indexPath.row]
        let cell = tableView.dequeueReusableCell(withIdentifier: "itemCell") as! VerticalCell
        cell.setData(recipe: item)
        return cell
    }
    
}
