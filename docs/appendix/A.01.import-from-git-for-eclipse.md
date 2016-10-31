---
title: Git リポジトリからのプロジェクトのインポート
description: この章では、Eclipse 上で、Git リポジトリからプロジェクトをインポートする手順を解説します。
keywords: ["android","training", "基礎", "スキル", "開発", "developer", "プログラミング", "git", "Eclipse"]

---

## Git リポジトリからのインポート

Eclipse のメニューの File > Import あるいは、Package Explorer で右クリックメニューから Import を選択します。

![Import Menu]({{site.baseurl}}/assets/04-01/import_context_menu.png)

次に表示されるメニューから、Git の中にある Projects from Git を選択し、Next。

![Select a way to import project]({{site.baseurl}}/assets/04-01/projects_from_git.png)

リポジトリの場所がどこにあるか選択します。<br />
ローカルにクローン済みである場合は、Local を、直接このウィザード上でクローンする場合は、URI を選択します。

## ローカルのリポジトリからのインポート

ローカルにあるリポジトリの場所を入力します。

![Select a local repository location]({{site.baseurl}}/assets/04-01/select_repository.png)

リポジトリの中から、プロジェクトを探索するルートのディレクトリを選択します。<br />
特に無ければ、Working Directory としておきます。

![Select a project root]({{site.baseurl}}/assets/04-01/select_project_dir.png)

インポートするプロジェクトを選択して、Finish。

![Select projects to import]({{site.baseurl}}/assets/04-01/import_project.png)

## リモートのリポジトリからのインポート

リモートリポジトリの場所を指定します。

![Set remote repo]({{site.baseurl}}/assets/04-01/set_source_remote_repo.png)

どのブランチをチェックアウトするか指定します。

![Select checking out branch]({{site.baseurl}}/assets/04-01/select_branch.png)

ローカルのどこにリポジトリをクローンするかを決めます。

![Select where to clone]({{site.baseurl}}/assets/04-01/select_local_dest_dir.png)

リポジトリの中から、プロジェクトを探索するルートのディレクトリを選択します。<br />
特に無ければ、Working Directory としておきます。

![Select a project root]({{site.baseurl}}/assets/04-01/select_project_dir.png)

インポートするプロジェクトを選択して、Finish。

![Select projects to import]({{site.baseurl}}/assets/04-01/import_project.png)
